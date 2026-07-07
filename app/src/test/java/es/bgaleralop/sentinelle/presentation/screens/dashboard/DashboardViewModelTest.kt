/*
 *
 *  Copyright (C) 2026 Sentinelle Team <bgaleralop@gmail.com>
 *
 *  This source code is property of Sentinelle Team.
 *  It is made available publicly for portfolio evaluation and educational purposes only.
 *  Commercial use, reproduction, or distribution in any digital storefront is
 *  strictly prohibited under the PolyForm Noncommercial License 1.0.0.
 *
 *  For full license details, see the LICENSE.md file in the root directory.
 *
 */

package es.bgaleralop.sentinelle.presentation.screens.dashboard

import android.util.Log
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import es.bgaleralop.sentinelle.domain.model.ModerationLog
import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import es.bgaleralop.sentinelle.domain.model.SparklinePoint
import es.bgaleralop.sentinelle.domain.model.enums.Platform
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.repository.AccountRepository
import es.bgaleralop.sentinelle.domain.repository.ModerationLogRepository
import es.bgaleralop.sentinelle.domain.repository.SparklineRepository
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var accountRepository: AccountRepository
    private lateinit var userRepository: UserRepository
    private lateinit var logRepository: ModerationLogRepository
    private lateinit var sparklineRepository: SparklineRepository
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        accountRepository = mockk(relaxed = true)
        userRepository = mockk()
        logRepository = mockk()
        sparklineRepository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given successful repository data, when init, then uiState becomes Success`() = runTest {
        // Given
        val tier = UserTier.FREE
        val accounts = listOf(
            SentinelleAccount(Platform.INSTAGRAM, "@user", "tk", "ref", 0, 0, id = 1)
        )
        val sparkline = listOf(SparklinePoint(10, 5))
        val logs = listOf(
            ModerationLog(1, 1, "c1", "author", "text", 1000L, false, "badword", "DELETED")
        )

        every { userRepository.getUserTier() } returns flowOf(tier)
        every { accountRepository.getActiveAccounts() } returns flowOf(accounts)
        every { sparklineRepository.getChecksSparklineToday(any()) } returns flowOf(sparkline)
        every { logRepository.getAllLogs() } returns flowOf(logs)

        // When
        viewModel = DashboardViewModel(
            accountRepository,
            userRepository,
            logRepository,
            sparklineRepository
        )

        // Then
        viewModel.uiSate.test {
            assertThat(awaitItem()).isInstanceOf(DashboardUiState.Loading::class.java)
            val successState = awaitItem() as DashboardUiState.Success
            assertThat(successState.userTier).isEqualTo(tier)
            assertThat(successState.recentModerationLogs).hasSize(1)
            assertThat(successState.recentModerationLogs[0].account.accountHandle).isEqualTo("@user")
        }
    }

    @Test
    fun `given a log without matching account, when loading data, then that log is ignored`() =
        runTest {
            // Given
            val tier = UserTier.FREE
            val accounts = emptyList<SentinelleAccount>()
            val sparkline = emptyList<SparklinePoint>()
            val logs = listOf(
                ModerationLog(1, 999, "c1", "author", "text", 1000L, false, "badword", "DELETED")
            )

            every { userRepository.getUserTier() } returns flowOf(tier)
            every { accountRepository.getActiveAccounts() } returns flowOf(accounts)
            every { sparklineRepository.getChecksSparklineToday(any()) } returns flowOf(sparkline)
            every { logRepository.getAllLogs() } returns flowOf(logs)

            // When
            viewModel = DashboardViewModel(
                accountRepository,
                userRepository,
                logRepository,
                sparklineRepository
            )

            // Then
            viewModel.uiSate.test {
                assertThat(awaitItem()).isInstanceOf(DashboardUiState.Loading::class.java)
                val successState = awaitItem() as DashboardUiState.Success
                assertThat(successState.recentModerationLogs).isEmpty()
            }
        }

    @Test
    fun `given repository error, when loading data, then uiState becomes Error`() = runTest {
        // Given
        every { userRepository.getUserTier() } returns flowOf(UserTier.FREE)
        every { accountRepository.getActiveAccounts() } returns flowOf(emptyList())
        every { sparklineRepository.getChecksSparklineToday(any()) } returns flowOf(emptyList())
        every { logRepository.getAllLogs() } returns flowOf(emptyList())

        // Simulate error in one of the flows by using a flow that throws
        every { logRepository.getAllLogs() } returns kotlinx.coroutines.flow.flow {
            throw Exception("Database Error")
        }

        // When
        viewModel = DashboardViewModel(
            accountRepository,
            userRepository,
            logRepository,
            sparklineRepository
        )

        // Then
        viewModel.uiSate.test {
            assertThat(awaitItem()).isInstanceOf(DashboardUiState.Loading::class.java)
            val state = awaitItem()
            assertThat(state).isInstanceOf(DashboardUiState.Error::class.java)
            assertThat((state as DashboardUiState.Error).message).isEqualTo("Database Error")
        }
    }
}
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

package es.bgaleralop.sentinelle.presentation.screens.config

import android.util.Log
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.usecase.ConfigUseCase
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
class ConfigViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var configUseCase: ConfigUseCase
    private lateinit var viewModel: ConfigViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        configUseCase = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given FREE tier, when init, then isAdvanceOptionEnabled is false`() = runTest {
        // Given
        every { configUseCase.getUserTier() } returns flowOf(UserTier.FREE)

        // When
        viewModel = ConfigViewModel(configUseCase)

        // Then
        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(ConfigUiState.Loading::class.java)
            val successState = awaitItem()

            assertThat(successState).isInstanceOf(ConfigUiState.Success::class.java)
            assertThat((successState as ConfigUiState.Success).isAdvanceOptionEnabled).isFalse()
            assertThat(successState.currentTier).isEqualTo(UserTier.FREE)
        }
    }

    @Test
    fun `given PRO tier, when init, then isAdvanceOptionEnabled is true`() = runTest {
        // Given
        every { configUseCase.getUserTier() } returns flowOf(UserTier.PRO)

        // When
        viewModel = ConfigViewModel(configUseCase)

        // Then
        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(ConfigUiState.Loading::class.java)
            val successState = awaitItem()

            assertThat(successState).isInstanceOf(ConfigUiState.Success::class.java)
            assertThat((successState as ConfigUiState.Success).isAdvanceOptionEnabled).isTrue()
            assertThat(successState.currentTier).isEqualTo(UserTier.PRO)
        }
    }
}
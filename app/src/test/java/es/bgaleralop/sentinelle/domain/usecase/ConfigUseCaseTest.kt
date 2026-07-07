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

package es.bgaleralop.sentinelle.domain.usecase

import com.google.common.truth.Truth.assertThat
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class ConfigUseCaseTest {

    private lateinit var userRepository: UserRepository
    private lateinit var configUseCase: ConfigUseCase

    @Before
    fun setUp() {
        userRepository = mockk()
        configUseCase = ConfigUseCase(userRepository)
    }

    // --- Tier Logic Tests ---

    @Test
    fun `given FREE tier, when canFilterEmojis is called, then it always returns false`() {
        // Given
        val tier = UserTier.FREE
        // First we set it to true using PRO tier (simulating persistence/previous state)
        configUseCase.changeEmojisFilter(UserTier.PRO)

        // When
        val result = configUseCase.canFilterEmojis(tier)

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `given FREE tier, when changeEmojisFilter is called, then it does not toggle and returns false`() {
        // Given
        val tier = UserTier.FREE
        val initialState = configUseCase.canFilterEmojis(tier)

        // When
        val newState = configUseCase.changeEmojisFilter(tier)

        // Then
        assertThat(initialState).isFalse()
        assertThat(newState).isFalse()
    }

    @Test
    fun `given PRO tier, when changeEmojisFilter is called, then it toggles the state`() {
        // Given
        val tier = UserTier.PRO
        val initialState = configUseCase.canFilterEmojis(tier)

        // When
        val newState = configUseCase.changeEmojisFilter(tier)

        // Then
        assertThat(newState).isNotEqualTo(initialState)
        assertThat(configUseCase.canFilterEmojis(tier)).isEqualTo(newState)
    }

    @Test
    fun `given FREE tier, when checking filters, then all filtering options are false`() {
        // Given
        val tier = UserTier.FREE

        // Force all to true using PRO
        configUseCase.changeEmojisFilter(UserTier.PRO)
        configUseCase.changeExternalLinksFilter(UserTier.PRO)
        configUseCase.changeAdvanceMatched(UserTier.PRO)

        // When & Then
        assertThat(configUseCase.canFilterEmojis(tier)).isFalse()
        assertThat(configUseCase.canFilterExternalLinks(tier)).isFalse()
        assertThat(configUseCase.canFilterAdvancedMatched(tier)).isFalse()
    }

    // --- Other Tests ---

    @Test
    fun `when changeDarkMode is called, then it toggles the state regardless of tier`() {
        // Given
        val initialState = configUseCase.isDarkMode()

        // When
        val newState = configUseCase.changeDarkMode()

        // Then
        assertThat(newState).isNotEqualTo(initialState)
        assertThat(configUseCase.isDarkMode()).isEqualTo(newState)
    }

    @Test
    fun `given a user tier, when getUserTier is called, then it returns the correct tier flow`() {
        // Given
        val expectedTier = UserTier.PRO
        every { userRepository.getUserTier() } returns flowOf(expectedTier)

        // When
        val resultFlow = configUseCase.getUserTier()

        // Then
        assertThat(resultFlow).isNotNull()
    }
}
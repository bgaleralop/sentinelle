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

import es.bgaleralop.sentinelle.domain.model.UserSettingsState
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 02-07-2026
 *
 *
 */
class ConfigUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    val settingsState: StateFlow<UserSettingsState> = userRepository.settingsState

    // Mockeada
    fun getUserTier() = settingsState.value.userTier

    fun canFilterEmojis(tier: UserTier): Boolean {
        if (tier == UserTier.FREE) return false
        return userRepository.getCachedSettings().isEmojisFilterEnabled
    }

    fun canFilterExternalLinks(tier: UserTier): Boolean {
        if (tier == UserTier.FREE) return false
        return userRepository.getCachedSettings().isExternalLinksFilterEnabled
    }

    fun canFilterAdvancedMatched(tier: UserTier): Boolean {
        if (tier == UserTier.FREE) return false
        return userRepository.getCachedSettings().isAdvancedMatchedFilterEnabled
    }

    fun isDarkMode() = userRepository.getCachedSettings().isDarkMode

    suspend fun changeEmojisFilter(tier: UserTier): Boolean {
        if (tier != UserTier.FREE) {
            val current = userRepository.getCachedSettings().isEmojisFilterEnabled
            userRepository.setEmojisFilter(!current)
        }
        return canFilterEmojis(tier)
    }

    suspend fun changeExternalLinksFilter(tier: UserTier): Boolean {
        if (tier != UserTier.FREE) {
            val current = userRepository.getCachedSettings().isExternalLinksFilterEnabled
            userRepository.setExternalLinks(!current)
        }
        return canFilterExternalLinks(tier)
    }

    suspend fun changeAdvanceMatched(tier: UserTier): Boolean {
        if (tier != UserTier.FREE) {
            val current = userRepository.getCachedSettings().isAdvancedMatchedFilterEnabled
            userRepository.setAdvancedMatchedFilter(!current)
        }
        return canFilterAdvancedMatched(tier)
    }

    suspend fun changeDarkMode(): Boolean {
        val current = userRepository.getCachedSettings().isDarkMode
        userRepository.setDarkMode(!current)
        return !current
    }
}
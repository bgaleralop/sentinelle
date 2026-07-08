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

import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.repository.UserRepository

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 02-07-2026
 *
 *
 */
class ConfigUseCase(
    private val userRepository: UserRepository,
) {
    private var isEmojisFilterActivated: Boolean = false
    private var isExternalLinksActivated: Boolean = false
    private var isAdvancedMatchedActivated: Boolean = false
    private var isDarkMode: Boolean = true

    // Mockeada
    suspend fun getUserTier() = userRepository.getUserTier()

    fun canFilterEmojis(tier: UserTier): Boolean {
        if (tier == UserTier.FREE) return false
        return isEmojisFilterActivated
    }

    fun canFilterExternalLinks(tier: UserTier): Boolean {
        if (tier == UserTier.FREE) return false
        return isExternalLinksActivated
    }

    fun canFilterAdvancedMatched(tier: UserTier): Boolean {
        if (tier == UserTier.FREE) return false
        return isAdvancedMatchedActivated
    }

    fun isDarkMode() = isDarkMode

    fun changeEmojisFilter(tier: UserTier): Boolean {
        if (tier != UserTier.FREE) {
            isEmojisFilterActivated = !isEmojisFilterActivated
        }
        return canFilterEmojis(tier)
    }

    fun changeExternalLinksFilter(tier: UserTier): Boolean {
        if (tier != UserTier.FREE) {
            isExternalLinksActivated = !isExternalLinksActivated
        }
        return canFilterExternalLinks(tier)
    }

    fun changeAdvanceMatched(tier: UserTier): Boolean {
        if (tier != UserTier.FREE) {
            isAdvancedMatchedActivated = !isAdvancedMatchedActivated
        }
        return canFilterAdvancedMatched(tier)
    }

    fun changeDarkMode(): Boolean {
        isDarkMode = !isDarkMode
        return isDarkMode
    }
}
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

import android.util.Log
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
    fun getUserTier() = userRepository.getUserTier()
    fun canFilterEmojis(): Boolean {
        Log.i("ConfigUseCase", "Devolviendo canFilterEmojis")
        return isEmojisFilterActivated
    }

    fun canFilterExternalLinks(): Boolean {
        return isExternalLinksActivated
    }

    fun canFilterAdvancedMatched(): Boolean {
        return isAdvancedMatchedActivated
    }

    fun isDarkMode() = isDarkMode

    fun changeEmojisFilter(): Boolean {
        isEmojisFilterActivated = !isEmojisFilterActivated
        return isEmojisFilterActivated
    }

    fun changeExternalLinksFilter(): Boolean {
        isExternalLinksActivated = !isExternalLinksActivated
        return isExternalLinksActivated
    }

    fun changeAdvanceMatched(): Boolean {
        isAdvancedMatchedActivated = !isAdvancedMatchedActivated
        return isAdvancedMatchedActivated
    }

    fun changeDarkMode(): Boolean {
        isDarkMode = !isDarkMode
        return isDarkMode
    }
}
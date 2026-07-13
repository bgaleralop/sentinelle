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

package es.bgaleralop.sentinelle.domain.model

import es.bgaleralop.sentinelle.domain.model.enums.UserTier

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 09-07-2026
 *
 * Data class that represent the user settings state.
 */
data class UserSettingsState(
    val userTier: UserTier = UserTier.FREE,
    val isEmojisFilterEnabled: Boolean = false,
    val isExternalLinksFilterEnabled: Boolean = false,
    val isAdvancedMatchedFilterEnabled: Boolean = false,
    val isDarkMode: Boolean = true,
    val lastFetch: Long = 0L
)

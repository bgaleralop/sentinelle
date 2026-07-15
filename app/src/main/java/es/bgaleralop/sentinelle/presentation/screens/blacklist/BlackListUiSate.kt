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

package es.bgaleralop.sentinelle.presentation.screens.blacklist

import es.bgaleralop.sentinelle.domain.model.BannedUser
import es.bgaleralop.sentinelle.domain.model.Word

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 15-07-2026
 *
 * State for the BlacklistScreen
 */
data class BlackListUiSate(
    val currentTab: Int = 0,
    val query: String = "",
    val words: List<Word> = emptyList(),
    val bannedUsers: List<BannedUser> = emptyList(),
    val filteredWords: List<Word> = emptyList(),
    val filteredBannedUsers: List<BannedUser> = emptyList()
)
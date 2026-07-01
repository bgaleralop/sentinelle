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

package es.bgaleralop.sentinelle.presentation.screens.accounts

import es.bgaleralop.sentinelle.domain.model.SentinelleAccount

sealed interface AccountUiState {
    object Loading : AccountUiState

    data class Success(
        val accounts: List<SentinelleAccount>,
        val accountCount: Int,
        val isMaxAccountsReached: Boolean = false
    ) : AccountUiState

    data class Error(val message: String) : AccountUiState
}
package es.bgaleralop.sentinelle.ui.screens.accounts

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
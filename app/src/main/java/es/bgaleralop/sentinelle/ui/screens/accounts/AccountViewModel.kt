package es.bgaleralop.sentinelle.ui.screens.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.repository.AccountRepository
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading)
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    init {
        loadAccountsData()
    }

    fun loadAccountsData() {
        viewModelScope.launch {
            _uiState.value = AccountUiState.Loading

            // Combinamos el flujo de cuentas del repositorio con el Tier del usuario
            combine(
                accountRepository.getActiveAccounts(),
                userRepository.getUserTier()
            ) { accounts, tier ->
                val maxAllowed = when (tier) {
                    UserTier.FREE -> 1
                    UserTier.PRO -> 5
                    UserTier.ENTERPRISE -> 50
                }

                AccountUiState.Success(
                    accounts = accounts,
                    accountCount = accounts.size,
                    isMaxAccountsReached = accounts.size >= maxAllowed
                )
            }.catch { e ->
                _uiState.value = AccountUiState.Error(e.message ?: "Unknow error")
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun removeAccount(accountId: Long) {
        viewModelScope.launch {
            accountRepository.deleteAccount(accountId)
        }
    }

    fun addAccount(account: SentinelleAccount) {
        viewModelScope.launch {
            accountRepository.addAccount(account)
        }
    }

    fun onNewCountButtonSubmitted() {

    }
}
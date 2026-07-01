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

package es.bgaleralop.sentinelle.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.bgaleralop.sentinelle.domain.model.DetailedCommentLog
import es.bgaleralop.sentinelle.domain.repository.AccountRepository
import es.bgaleralop.sentinelle.domain.repository.ModerationLogRepository
import es.bgaleralop.sentinelle.domain.repository.SparklineRepository
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * ViewModel del Dashboard.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository,
    private val logRepository: ModerationLogRepository,
    // Mockeamos la lógica de sparklines por simplicidad.
    private val sparklineRepository: SparklineRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiSate: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading

            val currentTime = System.currentTimeMillis()

            combine(
                userRepository.getUserTier(),
                accountRepository.getActiveAccounts(),
                sparklineRepository.getChecksSparklineToday(currentTime),
                logRepository.getAllLogs()
            ) { tier, accounts, sparklineData, logs ->
                val currentCounts = sparklineData.lastOrNull()?.count ?: 0

                val detailedLogs = logs.mapNotNull { log ->
                    val account = accounts.find { it.id == log.accountId }
                    account?.let {
                        DetailedCommentLog(
                            log = log,
                            account = it,
                            matchedWord = log.matchedWord
                        )
                    }
                }

                DashboardUiState.Success(
                    userTier = tier,
                    checksToday = currentCounts,
                    sparklineData = sparklineData,
                    recentModerationLogs = detailedLogs,
                    accountCount = accounts.size
                )
            }.catch { e ->
                _uiState.value = DashboardUiState.Error(e.message ?: "Error desconocido")
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
}
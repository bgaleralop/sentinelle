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

package es.bgaleralop.sentinelle.presentation.screens.config

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.usecase.ConfigUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 02-07-2026
 *
 * ViewModel de la pantalla de configuración.
 */
@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val configUseCase: ConfigUseCase,
) : ViewModel() {

    private val TAG = "ConfigViewModel"
    private val _uiState = MutableStateFlow<ConfigUiState>(ConfigUiState.Loading)
    val uiState: StateFlow<ConfigUiState> = _uiState.asStateFlow()

    init {
        loadConfigData()
    }

    private fun loadConfigData() {

        Log.d(TAG, "loadConfigData: Loading config data")

        viewModelScope.launch {
            Log.d(TAG, "Entrando a la corrutina del viewModel")
            Log.d(TAG, "Valor de uiState = ${_uiState.value}")
            _uiState.value = ConfigUiState.Loading
            Log.d(TAG, "Segundo valor de uiState = ${_uiState.value}")

            _uiState.value = ConfigUiState.Success(
                currentTier = UserTier.PRO,
                isEmojisFilterActivated = configUseCase.canFilterEmojis(),
                isExternalLinksActivated = configUseCase.canFilterExternalLinks(),
                isAdvanceMatchedActivated = configUseCase.changeAdvanceMatched(),
                isDarkMode = configUseCase.isDarkMode()
            )

            Log.d(TAG, "current value = ${_uiState.value}")
        }
    }

    fun updateConfigOption(option: FilterOption = FilterOption.EMOJIS) {
        when (option) {
            FilterOption.EMOJIS -> {
                Log.d(TAG, "updateConfigOption: Updating emojis")
                updateEmojis()
            }

            FilterOption.LINKS -> {
                Log.d(TAG, "updateConfigOption: Updating links")
                updateLinks()
            }

            FilterOption.MATCHED -> {
                Log.d(TAG, "updateConfigOption: Updating matched")
                updateMatched()
            }

            FilterOption.DARKMODE -> {
                Log.d(TAG, "updateConfigOption: Updating dark mode")
                updateDarkMode()
            }
        }

    }

    fun navigateToOption(option: NavigateToOption = NavigateToOption.BLACKLIST) {
        when (option) {
            NavigateToOption.COUNTS -> {
                Log.d(TAG, "navigateToOption: Navigating to accounts")
            }

            NavigateToOption.TIERS -> {
                Log.d(TAG, "navigateToOption: Navigating to tiers")
            }

            NavigateToOption.BLACKLIST -> {
                Log.d(TAG, "navigateToOption: Navigating to blacklist")
            }

            NavigateToOption.LEGAL -> {
                Log.d(TAG, "navigateToOption: Navigating to legal")
            }
        }
    }

    private fun updateEmojis() {
        if (_uiState.value is ConfigUiState.Success) {
            var currentState = _uiState.value as ConfigUiState.Success
            currentState = currentState.copy(
                isEmojisFilterActivated = configUseCase.changeEmojisFilter()
            )

            _uiState.value = currentState
        }
    }

    private fun updateLinks() {
        if (_uiState.value is ConfigUiState.Success) {
            var currentState = _uiState.value as ConfigUiState.Success
            currentState = currentState.copy(
                isExternalLinksActivated = configUseCase.changeExternalLinksFilter()
            )

            _uiState.value = currentState
        }
    }

    private fun updateMatched() {
        if (_uiState.value is ConfigUiState.Success) {
            var currentState = _uiState.value as ConfigUiState.Success
            currentState = currentState.copy(
                isAdvanceMatchedActivated = configUseCase.changeAdvanceMatched()
            )

            _uiState.value = currentState
        }
    }

    private fun updateDarkMode() {
        if (_uiState.value is ConfigUiState.Success) {
            var currentState = _uiState.value as ConfigUiState.Success
            currentState = currentState.copy(
                isDarkMode = configUseCase.changeDarkMode()
            )

            _uiState.value = currentState
        }


    }
}

enum class FilterOption {
    EMOJIS,
    LINKS,
    MATCHED,
    DARKMODE
}

enum class NavigateToOption {
    COUNTS,
    TIERS,
    BLACKLIST,
    LEGAL
}
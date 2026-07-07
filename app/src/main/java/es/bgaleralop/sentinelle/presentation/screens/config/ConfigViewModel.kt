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
            _uiState.value = ConfigUiState.Loading
            configUseCase.getUserTier().collect { tier ->
                _uiState.value = ConfigUiState.Success(
                    currentTier = tier,
                    isEmojisFilterActivated = configUseCase.canFilterEmojis(tier),
                    isExternalLinksActivated = configUseCase.canFilterExternalLinks(tier),
                    isAdvanceMatchedActivated = configUseCase.canFilterAdvancedMatched(tier),
                    isDarkMode = configUseCase.isDarkMode()
                )
            }
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
            val currentState = _uiState.value as ConfigUiState.Success
            _uiState.value = currentState.copy(
                isEmojisFilterActivated = configUseCase.changeEmojisFilter(currentState.currentTier)
            )
        }
    }

    private fun updateLinks() {
        if (_uiState.value is ConfigUiState.Success) {
            val currentState = _uiState.value as ConfigUiState.Success
            _uiState.value = currentState.copy(
                isExternalLinksActivated = configUseCase.changeExternalLinksFilter(currentState.currentTier)
            )
        }
    }

    private fun updateMatched() {
        if (_uiState.value is ConfigUiState.Success) {
            val currentState = _uiState.value as ConfigUiState.Success
            _uiState.value = currentState.copy(
                isAdvanceMatchedActivated = configUseCase.changeAdvanceMatched(currentState.currentTier)
            )
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
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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.bgaleralop.sentinelle.domain.usecase.ConfigUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
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
    private val _uiState = MutableStateFlow<ConfigUiState>(ConfigUiState.Loading)
    val uiState: StateFlow<ConfigUiState> = _uiState.asStateFlow()

    init {
        loadConfigData()
    }

    private fun loadConfigData() {
        viewModelScope.launch {
            _uiState.value = ConfigUiState.Loading

            _uiState.value = ConfigUiState.Success(
                currentTier = configUseCase.getUserTier().last(),
                isEmojisFilterActivated = configUseCase.canFilterEmojis(),
                isExternalLinksActivated = configUseCase.canFilterExternalLinks(),
                isAdvanceMatchedActivated = configUseCase.changeAdvanceMatched(),
                isDarkMode = configUseCase.isDarkMode()
            )
        }
    }

    fun updateConfigOption(option: FilterOption = FilterOption.EMOJIS) {
        when (option) {
            FilterOption.EMOJIS -> updateEmojis()
            FilterOption.LINKS -> TODO()
            FilterOption.MATCHED -> TODO()
            FilterOption.DARKMODE -> TODO()
        }

    }

    fun navigateToOption(option: NavigateToOption = NavigateToOption.BLACKLIST) {
        when (option) {
            NavigateToOption.COUNTS -> TODO()
            NavigateToOption.TIERS -> TODO()
            NavigateToOption.BLACKLIST -> TODO()
            NavigateToOption.LEGAL -> TODO()
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
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    val uiState: StateFlow<ConfigUiState> = configUseCase.settingsState
        .map { settings ->
            ConfigUiState.Success(
                currentTier = settings.userTier,
                isEmojisFilterActivated = settings.isEmojisFilterEnabled,
                isExternalLinksActivated = settings.isExternalLinksFilterEnabled,
                isAdvanceMatchedActivated = settings.isAdvancedMatchedFilterEnabled,
                isDarkMode = settings.isDarkMode
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ConfigUiState.Loading
        )

    fun updateConfigOption(option: FilterOption = FilterOption.EMOJIS) {
        viewModelScope.launch {
            val tier = (uiState.value as? ConfigUiState.Success)?.currentTier ?: return@launch
            when (option) {
                FilterOption.EMOJIS -> {
                    Log.d(TAG, "updateConfigOption: Updating emojis")
                    configUseCase.changeEmojisFilter(tier)
                }

                FilterOption.LINKS -> {
                    Log.d(TAG, "updateConfigOption: Updating links")
                    configUseCase.changeExternalLinksFilter(tier)
                }

                FilterOption.MATCHED -> {
                    Log.d(TAG, "updateConfigOption: Updating matched")
                    configUseCase.changeAdvanceMatched(tier)
                }

                FilterOption.DARKMODE -> {
                    Log.d(TAG, "updateConfigOption: Updating dark mode")
                    configUseCase.changeDarkMode()
                }
            }
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
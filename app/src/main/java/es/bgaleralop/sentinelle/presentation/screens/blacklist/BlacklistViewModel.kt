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

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.bgaleralop.sentinelle.presentation.screens.blacklist.components.BlackListUiSate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BlacklistViewModel @Inject constructor() : ViewModel() {
    private val _uiSate = MutableStateFlow<BlackListUiSate>(BlackListUiSate())
    val uiSate: StateFlow<BlackListUiSate> = _uiSate.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {}

    fun onUpdateQuery(updated: String) {
        _uiSate.update { currentState ->
            currentState.copy(
                query = updated
            )
        }
    }

    fun updateCurrentTab(tab: Int) {
        _uiSate.update { currentState ->
            currentState.copy(
                currentTab = tab
            )
        }
    }
}
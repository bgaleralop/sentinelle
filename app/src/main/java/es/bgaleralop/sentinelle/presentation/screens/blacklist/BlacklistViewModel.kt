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
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.model.enums.BlacklistAction
import es.bgaleralop.sentinelle.domain.model.enums.SearchType
import es.bgaleralop.sentinelle.domain.util.Normalizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 15-07-2026
 *
 * ViewModel for the BlacklistScreen
 */
@HiltViewModel
class BlacklistViewModel @Inject constructor() : ViewModel() {
    private val _uiSate = MutableStateFlow<BlackListUiSate>(BlackListUiSate())
    val uiSate: StateFlow<BlackListUiSate> = _uiSate.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        _uiSate.update { currentState ->
            currentState.copy(
                words = mockedWords,
                filteredWords = mockedWords
            )
        }
    }

    fun onUpdateQuery(updated: String) {
        viewModelScope.launch {
            _uiSate.update { currentState ->
                currentState.copy(
                    query = updated
                )
            }

            updateCurrentsList(updated)
        }
    }

    fun updateCurrentTab(tab: Int) {
        _uiSate.update { currentState ->
            currentState.copy(
                currentTab = tab,
                query = ""
            )
        }
        viewModelScope.launch {
            updateCurrentsList("")
        }
    }

    private fun updateCurrentsList(query: String) {
        if (query.isEmpty()) {
            _uiSate.update { currentState ->
                currentState.copy(
                    filteredWords = _uiSate.value.words,
                    filteredBannedUsers = _uiSate.value.bannedUsers
                )
            }
            return
        }
        if (_uiSate.value.currentTab == 0) {
            _uiSate.update { currentState ->
                val filtered = _uiSate.value.filteredWords.filter {
                    it.word.contains(
                        query,
                        ignoreCase = true
                    )
                }
                currentState.copy(
                    filteredWords = filtered
                )
            }
        } else {
            _uiSate.update { currentState ->
                val filtered = _uiSate.value.filteredBannedUsers.filter {
                    it.username.contains(
                        query,
                        ignoreCase = true
                    )
                }
                currentState.copy(
                    filteredBannedUsers = filtered
                )
            }
        }
    }

    fun deleteWord(id: Long) {
        viewModelScope.launch {
            _uiSate.update { currentState ->
                val words = _uiSate.value.words.filter { it.id != id }
                val filterWords = _uiSate.value.filteredWords.filter { it.id != id }
                currentState.copy(
                    words = words,
                    filteredWords = filterWords
                )
            }
        }
    }

    fun addWord(word: Word) {
        val newWord =
            word.copy(id = System.currentTimeMillis(), word = Normalizer.normalizeWord(word.word))

        viewModelScope.launch {
            _uiSate.update { currentState ->
                currentState.copy(
                    words = _uiSate.value.words.plus(newWord)
                )
            }
        }
    }
}

private val mockedWords = listOf<Word>(
    Word(
        id = 1,
        accountId = 1,
        word = "Machirulo",
        action = BlacklistAction.DELETE,
        matches = 5,
        searchType = SearchType.CONTAINING
    ),
    Word(
        id = 2,
        accountId = 2,
        word = "Feminazi",
        action = BlacklistAction.HIDE,
        matches = 0,
        searchType = SearchType.EXACT
    ),
    Word(
        id = 3,
        accountId = 1,
        word = "Cripto",
        action = BlacklistAction.HIDE,
        matches = 3,
        searchType = SearchType.CONTAINING
    )
)
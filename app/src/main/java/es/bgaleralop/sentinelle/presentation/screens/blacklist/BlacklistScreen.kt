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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.presentation.screens.blacklist.components.BlacklistUsersScreen
import es.bgaleralop.sentinelle.presentation.screens.blacklist.components.KeyWordsTabContent
import es.bgaleralop.sentinelle.presentation.screens.commons.MySearchBar
import es.bgaleralop.sentinelle.presentation.screens.commons.SecondaryScreenTopAppBar
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme
import kotlinx.coroutines.launch

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 13-07-2026
 *
 * Composable que renderiza la pantalla de Lista Negra.
 */

val viewModel = BlacklistViewModel()
@Composable
fun BlacklistScreen(
    modifier: Modifier = Modifier,
    //viewModel: BlacklistViewModel = hiltViewModel(),
    onGoBack: () -> Unit = {}
) {
    val viewModel = viewModel
    val uiState by viewModel.uiSate.collectAsState()

    val pagerState = rememberPagerState(initialPage = 0) { 2 }

    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("Palabras", "Usuarios")

    Scaffold(
        topBar = { SecondaryScreenTopAppBar(title = "Lista Negra", onGoBack = onGoBack) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            MySearchBar(
                query = uiState.query,
                placeholder = getPlaceholder(uiState.currentTab),
                onUpdate = { update -> viewModel.onUpdateQuery(update) },
                onSubmit = { }
            )
            PrimaryTabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                                viewModel.updateCurrentTab(index)
                            }
                        },
                        text = { Text(text = title) }
                    )
                }
            }

            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> KeyWordsTabContent(
                        words = uiState.filteredWords,
                        onDelete = { id -> viewModel.deleteWord(id) }
                    )
                    1 -> BlacklistUsersScreen()
                }
            }
        }

    }
}

private fun getPlaceholder(currentTab: Int) = when (currentTab) {
    0 -> "Buscar Palabras..."
    1 -> "Buscar Usuarios..."
    else -> "Buscar..."
}

@Preview(showBackground = true, name = "Test A - Dark UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun BlacklistScreenDarkPreview() {
    SentinelleTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            BlacklistScreen()
        }
    }
}

@Preview(showBackground = true, name = "Test B - Light UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun BlacklistScreenLightPreview() {
    SentinelleTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            BlacklistScreen()
        }
    }
}



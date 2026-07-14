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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.presentation.screens.blacklist.components.BlacklistUsersScreen
import es.bgaleralop.sentinelle.presentation.screens.commons.SecondaryScreenTopAppBar
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme
import kotlinx.coroutines.launch

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 13-07-2026
 *
 * Composable que renderiza la pantalla de Lista Negra.
 */
@Composable
fun BlacklistScreen(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val placeholder: String = rememberSaveable {
        when (pagerState.currentPage) {
            0 -> "Buscar Palabras... "
            1 -> "Buscar Usuarios... "
            else -> "Buscar ..."
        }
    }
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
            modifier = modifier.padding(paddingValues)
        ) {
            MySearchBar(
                placeholder = placeholder
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
                            }
                        },
                        text = { Text(text = title) }
                    )
                }
            }

            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> BlacklistWordsScreen()
                    1 -> BlacklistUsersScreen()
                }
            }
        }

    }
}

@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    placeholder: String = "",
    onUpdate: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
) {
    OutlinedTextField(
        value = query,
        onValueChange = { onUpdate(it) },
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        maxLines = 1,
        shape = ShapeDefaults.ExtraLarge,
        suffix = {
            Icon(
                imageVector = Icons.Default.Search,
                null,
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = { onSubmit() }
                )
            )
        },
        colors = TextFieldDefaults.colors(),
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )
}

@Composable
fun BlacklistWordsScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
) {

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

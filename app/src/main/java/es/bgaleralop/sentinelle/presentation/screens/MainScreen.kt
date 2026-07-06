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

package es.bgaleralop.sentinelle.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.bgaleralop.sentinelle.presentation.navigation.AppNavHost
import es.bgaleralop.sentinelle.presentation.navigation.ConfigRoute
import es.bgaleralop.sentinelle.presentation.navigation.HomeRoute
import es.bgaleralop.sentinelle.presentation.screens.commons.BottomNavigationBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Control dinámico de visibilidad para la barra inferior
    val showBottomBar = currentDestination?.hasRoute<HomeRoute>() == true ||
            currentDestination?.hasRoute<ConfigRoute>() == true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController, currentDestination)
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
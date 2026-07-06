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

package es.bgaleralop.sentinelle.presentation.screens.commons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import es.bgaleralop.sentinelle.presentation.navigation.ConfigRoute
import es.bgaleralop.sentinelle.presentation.navigation.HomeRoute

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, null) },
            label = { Text("Notificaciones") },
            selected = currentDestination?.hasRoute<HomeRoute>() == true,
            onClick = { navController.navigateToTab(HomeRoute) })
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, null) },
            label = { Text("Cuentas") },
            selected = false,
            onClick = {})
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, null) },
            label = { Text("Ajustes") },
            selected = currentDestination?.hasRoute<ConfigRoute>() == true,
            onClick = { navController.navigateToTab(ConfigRoute) })
    }
}

private fun NavHostController.navigateToTab(route: Any) {
    this.navigate(route) {
        // Usamos @navigateToTab para indicarle a Kotlin que use el NavHostController de fuera
        popUpTo(this@navigateToTab.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
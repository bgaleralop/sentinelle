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

package es.bgaleralop.sentinelle.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import es.bgaleralop.sentinelle.presentation.navigation.AccountsRoute
import es.bgaleralop.sentinelle.presentation.navigation.ConfigRoute
import es.bgaleralop.sentinelle.presentation.navigation.PlansRoute
import es.bgaleralop.sentinelle.presentation.screens.accounts.AccountScreen
import es.bgaleralop.sentinelle.presentation.screens.config.ConfigScreen
import es.bgaleralop.sentinelle.presentation.screens.config.NavigateToOption
import es.bgaleralop.sentinelle.presentation.screens.plans.PlansScreen

fun NavGraphBuilder.configGraph(
    navController: NavController,
    onNavigateToExternalStore: () -> Unit,
) {
    // Pantalla Principal de Configuración.
    composable<ConfigRoute> {
        ConfigScreen(
            onNavigateTo = { option ->
                when (option) {
                    NavigateToOption.COUNTS -> navController.navigate(AccountsRoute)
                    NavigateToOption.TIERS -> navController.navigate(PlansRoute)
                    NavigateToOption.BLACKLIST -> TODO()
                    NavigateToOption.LEGAL -> TODO()
                }
            }
        )
    }

    // Subpantalla: Cuentas Vinculadas.
    composable<AccountsRoute> {
        AccountScreen(
            onBack = { navController.popBackStack() },
        )
    }

    composable<PlansRoute> {
        PlansScreen(
            onGoBack = { navController.popBackStack() },
            onGoToSubscription = { TODO() }
        )
    }
}
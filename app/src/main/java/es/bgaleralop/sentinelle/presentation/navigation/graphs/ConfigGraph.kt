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
import es.bgaleralop.sentinelle.presentation.navigation.ConfigRoute
import es.bgaleralop.sentinelle.presentation.screens.config.ConfigScreen

fun NavGraphBuilder.configGraph(navController: NavController) {
    // Pantalla Principal de Configuración.
    composable<ConfigRoute> {
        ConfigScreen()
    }

    // Subpantalla: Cuentas Vinculadas.
}
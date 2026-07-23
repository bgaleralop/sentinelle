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

package es.bgaleralop.sentinelle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import es.bgaleralop.sentinelle.presentation.screens.MainScreen
import es.bgaleralop.sentinelle.presentation.screens.dashboard.DashboardUiState
import es.bgaleralop.sentinelle.presentation.screens.dashboard.DashboardViewModel
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            dashboardViewModel.uiSate.value is DashboardUiState.Loading
        }

        enableEdgeToEdge()
        setContent {
            SentinelleTheme {
                MainScreen()
            }
        }
    }
}


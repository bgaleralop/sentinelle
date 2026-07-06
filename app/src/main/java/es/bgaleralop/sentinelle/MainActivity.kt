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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import es.bgaleralop.sentinelle.presentation.screens.config.ConfigScreen
import es.bgaleralop.sentinelle.presentation.screens.config.ConfigViewModel
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<ConfigViewModel>()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SentinelleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConfigScreen(viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


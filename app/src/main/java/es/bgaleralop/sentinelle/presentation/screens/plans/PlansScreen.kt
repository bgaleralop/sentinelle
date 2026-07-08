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

package es.bgaleralop.sentinelle.presentation.screens.plans

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.presentation.screens.commons.SecondaryScreenTopAppBar
import es.bgaleralop.sentinelle.presentation.screens.plans.components.FreeTierCard
import es.bgaleralop.sentinelle.presentation.screens.plans.components.PlanScreenHeader
import es.bgaleralop.sentinelle.presentation.screens.plans.components.ProTierCard
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 27-06-2026
 *
 * Composable que renderiza la pantalla de planes de tier.
 */

@Composable
fun PlansScreen(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit = {},
    onGoToSubscription: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SecondaryScreenTopAppBar(
                onGoBack = onGoBack,
                title = "Planes"
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
            ) {
                PlanScreenHeader()
                FreeTierCard(selected = true, onSelected = {})
                ProTierCard(selected = false, onSelected = {})

                Button(
                    onClick = { onGoToSubscription() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Suscribirse con Google Play",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Box {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {

                        Text(
                            text = "Suscripción mensual recurrente gestionada de forma segura por Google Play.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))
                        Text(
                            text = "Cancela cuando quieras desde tu perfil de la tienda.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true, name = "Test A - Dark UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun PlanScreenDarkModePreview() {
    SentinelleTheme(darkTheme = true) {
        PlansScreen()
    }
}

@Preview(showBackground = true, name = "Test B - Light UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun PlanScreenLightModePreview() {
    SentinelleTheme(darkTheme = false) {
        PlansScreen()
    }
}
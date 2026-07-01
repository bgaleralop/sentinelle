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

package es.bgaleralop.sentinelle.presentation.screens.config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.presentation.screens.config.components.ConfigOptionCard
import es.bgaleralop.sentinelle.presentation.screens.config.components.ConfigOptionCardSwitch
import es.bgaleralop.sentinelle.presentation.screens.config.components.ConfigPlanCard
import es.bgaleralop.sentinelle.presentation.screens.config.components.ConfigScreenHeader
import es.bgaleralop.sentinelle.presentation.screens.config.components.ConfigSectionTitle
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 29-06-2026
 *
 * Composable that render the config screen.
 */
@Composable
fun ConfigScreen(modifier: Modifier = Modifier) {
    Scaffold { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                ConfigScreenHeader(title = "Ajustes")
                Box {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        ConfigSectionTitle("Cuentas")
                        ConfigPlanCard(tier = UserTier.PRO)
                        ConfigOptionCard(
                            title = "Cuentas Vinculadas",
                            buttonTitle = "Ver Cuentas",
                            action = {}
                        )
                    }
                }
                Box {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        ConfigSectionTitle("Filtros y Reglas")
                        ConfigOptionCard(
                            title = "Blacklist",
                            buttonTitle = "Ver",
                            action = {}
                        )

                        ConfigOptionCardSwitch(
                            title = "Filtrado de emojis",
                            description = "Oculta comentarios con mas de 5 emojis repetidos.",
                            action = {},
                            checked = false,
                            tier = UserTier.PRO
                        )
                        ConfigOptionCardSwitch(
                            title = "Bloquear enlaces externos",
                            description = "Elimina enlaces de spam o phishing",
                            tier = UserTier.FREE,
                            checked = true,
                            action = {}
                        )
                        ConfigOptionCardSwitch(
                            title = "Detección de Insultos Críticos",
                            description = "Algoritmo local tolerante a variaciones tipográficas",
                            tier = UserTier.PRO,
                            checked = true,
                            action = {}
                        )
                    }
                }
                Box {
                    Column {
                        ConfigSectionTitle("Preferencias Visuales")
                        ConfigOptionCard(
                            title = "Tema de la app",
                            buttonTitle = "Modo Oscuro",
                            action = {}
                        )
                    }
                }
                Box {
                    Column {
                        ConfigSectionTitle("Seguridad y Almacenamiento")
                        ConfigOptionCard(
                            title = "Políticas y Privacidad",
                            buttonTitle = "Ver Legal",
                            action = {}
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Sentinelle para Android - v1.0.0(Local first)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Test A - Dark UI Mode")
@Composable
private fun ConfigScreenDarkModePreview() {
    SentinelleTheme(darkTheme = true) {
        Surface {
            ConfigScreen()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Test B - Light UI Mode")
@Composable
private fun ConfigScreenLightModePreview() {
    SentinelleTheme(darkTheme = false) {
        Surface {
            ConfigScreen()
        }
    }
}

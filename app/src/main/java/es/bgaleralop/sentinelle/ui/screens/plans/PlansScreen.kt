package es.bgaleralop.sentinelle.ui.screens.plans

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
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.ui.screens.plans.components.FreeTierCard
import es.bgaleralop.sentinelle.ui.screens.plans.components.PlanScreenHeader
import es.bgaleralop.sentinelle.ui.screens.plans.components.ProTierCard
import es.bgaleralop.sentinelle.ui.theme.SentinelleTheme

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 27-06-2026
 *
 * Composable que renderiza la pantalla de planes de tier.
 */

@Composable
fun PlansScreen(currentTier: UserTier, onSubscribe: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
            ) {
                PlanScreenHeader()
                FreeTierCard(selected = true, onSelected = {})
                ProTierCard(selected = false, onSelected = {})

                Button(
                    onClick = { onSubscribe() },
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
        PlansScreen(currentTier = UserTier.FREE, onSubscribe = {})
    }
}

@Preview(showBackground = true, name = "Test B - Light UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun PlanScreenLightModePreview() {
    SentinelleTheme(darkTheme = false) {
        PlansScreen(currentTier = UserTier.PRO, onSubscribe = {})
    }
}
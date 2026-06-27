package es.bgaleralop.sentinelle.ui.screens.plans.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProTierCard(selected: Boolean, onSelected: () -> Unit, modifier: Modifier = Modifier){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(4.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column() {
                    Text(
                        text = "Plan Creador Pro",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Hasta 5 cuentas",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.weight(0.4f))
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "4,99€",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "/mes",
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            PlanCardElement(text = "Hasta 5 cuentas")
            Spacer(modifier = Modifier.padding(4.dp))
            PlanCardElement(text = "Filtrado de comentarios sin limite")
            Spacer(modifier = Modifier.padding(4.dp))
            PlanCardElement(text = "Filtrado avanzado inteligente")
            Spacer(modifier = Modifier.padding(4.dp))
            PlanCardElement(text = "Estadísticas ampliadas")
        }


    }
}
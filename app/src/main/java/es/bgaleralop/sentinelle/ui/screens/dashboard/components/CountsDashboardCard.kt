package es.bgaleralop.sentinelle.ui.screens.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.SparklinePoint
import es.bgaleralop.sentinelle.ui.screens.dashboard.DashboardUiState

@Composable
fun CountsDashboardCard(state: DashboardUiState.Success, sparks: List<SparklinePoint>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Comentarios filtrados hoy", style = MaterialTheme.typography.labelSmall)
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "${state.checksToday}",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(16.dp))

                SparklineGraph(
                    points = sparks,
                    modifier = Modifier.size(100.dp, 40.dp)
                )
            }
        }
    }
}
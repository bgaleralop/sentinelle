package es.bgaleralop.sentinelle.ui.screens.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.DetailedCommentLog
import es.bgaleralop.sentinelle.utils.selectCardBorderColor

@Composable
fun ModeratedCommentCard(detailedLog: DetailedCommentLog) {
    // Definimos el color del borde sutil de neón basado en el matchedWord
    val borderColor = when (detailedLog.matchedWord) {
        "seguidores" -> Color(0xFF5D5FEF).copy(alpha = 0.5f) // ElectricIndigo
        "peor" -> Color(0xFF00C9A7).copy(alpha = 0.5f) // SeafoamTeal
        else -> Color.Transparent
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Aplicamos el BorderStroke sutil para el efecto de neón
        border = BorderStroke(2.dp, selectCardBorderColor(detailedLog.account.platform))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = detailedLog.account.accountHandle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Comentario original y detalles (resaltando palabras como seguidores y peor)
                Text(
                    text = detailedLog.log.commentText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            // Botón Eliminar
            Button(
                onClick = { /* Acción para eliminar de la lista local de moderados */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text(text = "Eliminar", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}
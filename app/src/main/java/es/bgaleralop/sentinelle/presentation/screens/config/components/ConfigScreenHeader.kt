package es.bgaleralop.sentinelle.presentation.screens.config.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 30-06-2026
 *
 * Header for the config screen.
 */
@Composable
fun ConfigScreenHeader(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}
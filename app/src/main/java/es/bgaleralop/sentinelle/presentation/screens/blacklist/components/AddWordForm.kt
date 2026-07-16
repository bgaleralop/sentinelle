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

package es.bgaleralop.sentinelle.presentation.screens.blacklist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.enums.BlacklistAction
import es.bgaleralop.sentinelle.domain.model.enums.SearchType
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 16-07-2026
 *
 * Composable que renderiza el formulario para añadir una palabra a la lista negra.
 */
@Composable
fun AddWordForm(
    onDismiss: () -> Unit,
    onSave: (String, SearchType, BlacklistAction) -> Unit,
    modifier: Modifier = Modifier
) {
    var word by remember { mutableStateOf("") }
    var searchType by remember { mutableStateOf(SearchType.EXACT) }
    var takenAction by remember { mutableStateOf(BlacklistAction.HIDE) }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Nueva palabra bloqueada",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = word,
            onValueChange = { word = it },
            label = { Text(text = "Palabra", style = MaterialTheme.typography.labelLarge) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tipo de filtrado")
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(selected = searchType == SearchType.CONTAINING, onClick = {
                searchType =
                    SearchType.CONTAINING
            })
            Text("Contiene")
            RadioButton(
                selected = searchType == SearchType.EXACT,
                onClick = { searchType = SearchType.EXACT })
            Text("Exacta")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Acción ha tomar")
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(selected = takenAction == BlacklistAction.HIDE, onClick = {
                takenAction =
                    BlacklistAction.HIDE
            })
            Text("Ocultar")
            RadioButton(
                selected = takenAction == BlacklistAction.DELETE,
                onClick = { takenAction = BlacklistAction.DELETE })
            Text("Eliminar")
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
            Button(onClick = { onSave(word, searchType, takenAction) }) { Text("Guardar") }
        }
    }
}


@Preview(showBackground = true, name = "Test A - Dark UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun AddWordFormDarkPreview() {
    SentinelleTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AddWordForm(onDismiss = {}, onSave = { _, _, _ -> })
        }
    }
}

@Preview(showBackground = true, name = "Test B - Light UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun AddWordFormLightPreview() {
    SentinelleTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AddWordForm(onDismiss = {}, onSave = { _, _, _ -> })
        }
    }
}
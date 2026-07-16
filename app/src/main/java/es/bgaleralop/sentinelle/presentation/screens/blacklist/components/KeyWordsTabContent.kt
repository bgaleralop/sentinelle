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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.Word

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 15-07-2026
 *
 * Composable que renderiza la pestaña de las keywords en la BlacklistScreen.
 */
@Composable
fun KeyWordsTabContent(
    words: List<Word>,
    onDelete: (Long) -> Unit,
    onChangeFilter: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(bottom = 48.dp)
    ) {
        items(words) { word ->
            KeywordCard(word, onDelete = onDelete, onChangeFilter = onChangeFilter)
        }
    }
}

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 15-07-2026
 *
 * Composable que renderiza cada una de las tarjetas de una palabra.
 */
@Composable
fun KeywordCard(
    word: Word,
    onDelete: (Long) -> Unit,
    onChangeFilter: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = word.word,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${word.matches} bans producidos",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FilterChip(
                selected = false,
                onClick = { onChangeFilter(word.id) },
                label = {
                    Text(
                        text = word.searchType.name,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                shape = ShapeDefaults.ExtraLarge,
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = { onDelete(word.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar palabra",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
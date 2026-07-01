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

package es.bgaleralop.sentinelle.ui.screens.accounts.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.enums.UserTier

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 27-06-2026
 *
 * Composable que renderiza el encabezado de la pantalla de cuentas.
 */
@Composable
fun AccountsHeader(title: String, tier: UserTier, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(
            modifier = Modifier
                .width(16.dp)
        )
        Surface(
            modifier = Modifier.padding(bottom = 4.dp, end = 16.dp),
            color = Color(0xFF00C9A7).copy(alpha = 0.15f),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = tier.name,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00C9A7)
            )
        }
    }
}
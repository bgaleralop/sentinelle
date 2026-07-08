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

package es.bgaleralop.sentinelle.presentation.screens.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.R
import es.bgaleralop.sentinelle.core.utils.selectCardBorderColor
import es.bgaleralop.sentinelle.domain.model.DetailedCommentLog
import es.bgaleralop.sentinelle.domain.model.ModerationLog
import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import es.bgaleralop.sentinelle.domain.model.enums.Platform
import es.bgaleralop.sentinelle.presentation.theme.SentinelleTheme

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * Composable que renderiza un Card de Moderación.
 */
@Composable
fun ModeratedCommentCard(detailedLog: DetailedCommentLog, modifier: Modifier = Modifier) {
    val borderColor = selectCardBorderColor(detailedLog.account.platform)
    val painterResource = when (detailedLog.account.platform) {
        Platform.INSTAGRAM -> R.drawable.ic_instagram
        Platform.TIKTOK -> R.drawable.ic_tiktok
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(2.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(painterResource),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = detailedLog.log.authorUsername,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Comentario original y detalles (resaltando palabras como seguidores y peor)
            Text(
                text = buildModeratedText(
                    detailedLog.log.commentText,
                    detailedLog.matchedWord ?: ""
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(modifier = Modifier.weight(0.6f)) {
                Row {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "hace ${calculatedTimeSince(detailedLog.log.timestamp)}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Row {
                // Botón Bloquear.
                Button(
                    onClick = {/* Acción para bloquear */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Report,
                        contentDescription = "Bloquear usuario.",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                // Botón Eliminar
                Button(
                    onClick = { /* Acción para eliminar de la lista local de moderados */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        contentColor = Color.White
                    ),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar/Ocultar comentario",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }

}

@Composable
fun buildModeratedText(
    fullText: String,
    keyword: String
): AnnotatedString {
    return buildAnnotatedString {
        append(fullText)

        val regex = Regex(Regex.escape(keyword), RegexOption.IGNORE_CASE)

        regex.findAll(fullText).forEach { matchResult ->
            addStyle(
                style = SpanStyle(
                    background = MaterialTheme.colorScheme.tertiary.copy(0.4f),
                ),
                start = matchResult.range.first,
                end = matchResult.range.last + 1
            )
        }
    }
}

private fun calculatedTimeSince(timestamp: Long): String {
    val currentTime = System.currentTimeMillis()
    val timeDifference = currentTime - timestamp
    val minutes = timeDifference / (1000 * 60)

    return if (minutes < 60) {
        "$minutes min"
    } else {
        val hours = minutes / 60
        "$hours h"
    }
}

@Preview(showBackground = true, name = "Test A - Dark UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun ModeratedCommentCardDarkModePreview() {
    SentinelleTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ModeratedCommentCard(detailedLog = mockData)
        }
    }
}

@Preview(showBackground = true, name = "Test A - Light UI Mode", widthDp = 390, heightDp = 844)
@Composable
fun ModeratedCommentCardLightModePreview() {
    SentinelleTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ModeratedCommentCard(detailedLog = mockData)
        }
    }
}

private val fakeInstagramAccount = SentinelleAccount(
    id = 1,
    platform = Platform.INSTAGRAM,
    accountHandle = "@ig_spammer_x",
    accessToken = "mock_token_1",
    refreshToken = "mock_refresh_token_1",
    accessTokenExpiration = 234234,
    refreshTokenExpiration = 234234,
    isActive = true
)

private val mockData = DetailedCommentLog(
    log = ModerationLog(
        accountId = 1,
        commentId = "msg_001",
        authorUsername = "bot_seller",
        commentText = "¡Increíble oferta! Te vendo seguidores muy baratos. Escríbeme!",
        timestamp = System.currentTimeMillis() - 1000 * 60 * 25,
        isSpam = true,
        matchedWord = "seguidores",
        actionTaken = "FLAGGED"
    ),
    account = fakeInstagramAccount,
    matchedWord = "seguidores"
)

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

package es.bgaleralop.sentinelle.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ElectricIndigo,
    secondary = SeafoamTeal,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    onBackground = DarkOnSurface
)

private val LightColorScheme = lightColorScheme(
    primary = ElectricIndigo,
    secondary = SeafoamTeal,
    background = LightBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    onBackground = LightOnSurface,
    tertiary = SpamHighlightColor
)

private val SentinelleColorScheme = darkColorScheme(
    primary = ElectricIndigo,
    onPrimary = OffWhite,
    secondary = SeafoamTeal,
    onSecondary = MidnightNavy,
    background = MidnightNavy,
    onBackground = OffWhite,
    surface = MidnightNavyVariant,
    onSurface = OffWhite,
    surfaceVariant = MidnightNavyVariant,
    onSurfaceVariant = SlateGrey,
    error = ErrorRed,
    tertiary = SeafoamTeal.copy(alpha = 0.5f),
)

@Composable
fun SentinelleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) SentinelleColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
package es.bgaleralop.sentinelle.presentation.theme

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
    tertiary = SeafoamTeal.copy(alpha = 0.5f)
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
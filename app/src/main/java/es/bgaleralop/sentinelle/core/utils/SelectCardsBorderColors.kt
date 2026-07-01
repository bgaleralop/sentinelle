package es.bgaleralop.sentinelle.core.utils

import androidx.compose.ui.graphics.Color
import es.bgaleralop.sentinelle.domain.model.enums.Platform

fun selectCardBorderColor(platform: Platform): Color {
    return when (platform) {
        Platform.INSTAGRAM -> Color(0xFF5D5FEF).copy(alpha = 0.5f)
        Platform.TIKTOK -> Color(0xFF00C9A7).copy(alpha = 0.5f)
    }
}
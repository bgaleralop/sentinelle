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

package es.bgaleralop.sentinelle.utils

import androidx.compose.ui.graphics.Color
import es.bgaleralop.sentinelle.domain.model.enums.Platform

fun selectCardBorderColor(platform: Platform ): Color {
    return when (platform) {
        Platform.INSTAGRAM -> Color(0xFF5D5FEF).copy(alpha = 0.5f)
        Platform.TIKTOK -> Color(0xFF00C9A7).copy(alpha = 0.5f)
    }
}
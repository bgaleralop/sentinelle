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

package es.bgaleralop.sentinelle.presentation.screens.dashboard

import es.bgaleralop.sentinelle.domain.model.DetailedCommentLog
import es.bgaleralop.sentinelle.domain.model.SparklinePoint
import es.bgaleralop.sentinelle.domain.model.enums.UserTier

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * Estados de la pantalla Dashboard.
 */
sealed interface DashboardUiState {
    object Loading : DashboardUiState

    data class Success(
        val userTier: UserTier,
        val checksToday: Int,
        val sparklineData: List<SparklinePoint>,
        val recentModerationLogs: List<DetailedCommentLog>,
        val accountCount: Int
    ) : DashboardUiState

    data class Error(val message: String) : DashboardUiState
}
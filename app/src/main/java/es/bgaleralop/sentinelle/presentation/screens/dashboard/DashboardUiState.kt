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
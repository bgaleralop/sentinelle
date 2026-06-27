package es.bgaleralop.sentinelle.domain.repository

import es.bgaleralop.sentinelle.domain.model.SparklinePoint
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * Repositorio de Sparkline.
 */
interface SparklineRepository {
    /**
     * Recupera los puntos de control horario para dibujar la gráfica del día actual.
     */
    fun getChecksSparklineToday(startTimestamp: Long): Flow<List<SparklinePoint>>
}

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

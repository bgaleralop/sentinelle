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

package es.bgaleralop.sentinelle.data.repository

import es.bgaleralop.sentinelle.domain.model.SparklinePoint
import es.bgaleralop.sentinelle.domain.repository.SparklineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SparklineRepositoryImpl : SparklineRepository {
    private val fakeSparklineData = listOf(
        SparklinePoint(hour = 8, count = 2),
        SparklinePoint(hour = 10, count = 5),
        SparklinePoint(hour = 12, count = 9),
        SparklinePoint(hour = 14, count = 14) // Pico actual coincidente con el contador
    )

    override fun getChecksSparklineToday(startTimestamp: Long): Flow<List<SparklinePoint>> {
        return flowOf(fakeSparklineData)
    }
}
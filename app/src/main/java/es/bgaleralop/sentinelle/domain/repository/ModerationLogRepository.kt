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

import es.bgaleralop.sentinelle.domain.model.ModerationLog
import es.bgaleralop.sentinelle.domain.model.WordStat
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Contract to feed the control panel, daily statistics and history
 */
interface ModerationLogRepository {
    fun getLogsByAccount(accountId: Long): Flow<List<ModerationLog>>
    fun getAllLogs(): Flow<List<ModerationLog>>
    suspend fun saveLog(log: ModerationLog)
    suspend fun updateLogAction(logId: Long, newAction: String)
    suspend fun getChecksCountToday(accountId: Long, startOfDay: Long): Int
    suspend fun getSpamCountToday(accountId: Long, startOfDay: Long): Int
    fun getMostActiveSpamWords(accountId: Long): Flow<List<WordStat>>
}
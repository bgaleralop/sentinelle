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
package es.bgaleralop.sentinelle.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.bgaleralop.sentinelle.data.local.entity.ModerationLogEntity
import es.bgaleralop.sentinelle.domain.model.WordStat
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Data access object for the moderation log table.
 */
@Dao
interface ModerationLogDao {
    @Query("SELECT * FROM moderation_logs WHERE accountId = :accountId ORDER BY timestamp DESC")
    fun getLogsByAccount(accountId: Long): Flow<List<ModerationLogEntity>>

    @Query("SELECT * FROM moderation_logs ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<ModerationLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: ModerationLogEntity)

    @Query("UPDATE moderation_logs SET actionTaken = :newAction WHERE id = :logId")
    suspend fun updateLogAction(logId: Long, newAction: String)

    @Query("SELECT COUNT(*) FROM moderation_logs WHERE accountId = :accountId AND timestamp >= :startOfDay")
    suspend fun getChecksCountToday(accountId: Long, startOfDay: Long): Int

    @Query("SELECT COUNT(*) FROM moderation_logs WHERE accountId = :accountId AND isSpam = 1 AND timestamp >= :startOfDay")
    suspend fun getSpamCountToday(accountId: Long, startOfDay: Long): Int

    @Query("SELECT matchedWord as word, COUNT(*) as count FROM moderation_logs WHERE accountId = :accountId AND isSpam = 1 AND matchedWord IS NOT NULL GROUP BY matchedWord ORDER BY count DESC")
    fun getMostActiveSpamWords(accountId: Long): Flow<List<WordStat>>
}
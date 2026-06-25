package es.bgaleralop.sentinelle.data.repository

import es.bgaleralop.sentinelle.data.local.dao.ModerationLogDao
import es.bgaleralop.sentinelle.data.local.mappers.toDomain
import es.bgaleralop.sentinelle.data.local.mappers.toEntity
import es.bgaleralop.sentinelle.domain.model.ModerationLog
import es.bgaleralop.sentinelle.domain.model.WordStat
import es.bgaleralop.sentinelle.domain.repository.ModerationLogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ModerationLogRepositoryImpl(private val logDao: ModerationLogDao) : ModerationLogRepository {
    override fun getLogsByAccount(accountId: Long): Flow<List<ModerationLog>> = logDao.getLogsByAccount(accountId)
        .map { list -> list.map { it.toDomain() } }.flowOn(Dispatchers.IO)

    override fun getAllLogs(): Flow<List<ModerationLog>> = logDao.getAllLogs()
        .map { list -> list.map { it.toDomain() } }.flowOn(Dispatchers.IO)

    override suspend fun saveLog(log: ModerationLog) = withContext(Dispatchers.IO) {
        logDao.insertLog(log.toEntity())
    }

    override suspend fun updateLogAction(logId: Long, newAction: String) = withContext(Dispatchers.IO) {
        logDao.updateLogAction(logId, newAction)
    }

    override suspend fun getChecksCountToday(accountId: Long, startOfDay: Long): Int = withContext(Dispatchers.IO) {
        logDao.getChecksCountToday(accountId, startOfDay)
    }

    override suspend fun getSpamCountToday(accountId: Long, startOfDay: Long): Int = withContext(Dispatchers.IO) {
        logDao.getSpamCountToday(accountId, startOfDay)
    }

    override fun getMostActiveSpamWords(accountId: Long): Flow<List<WordStat>> = logDao.getMostActiveSpamWords(accountId).flowOn(Dispatchers.IO)
}
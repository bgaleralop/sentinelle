package es.bgaleralop.sentinelle.data.repository

import es.bgaleralop.sentinelle.data.local.dao.BannedUserDao
import es.bgaleralop.sentinelle.data.local.mappers.toDomain
import es.bgaleralop.sentinelle.data.local.mappers.toEntity
import es.bgaleralop.sentinelle.domain.model.BannedUser
import es.bgaleralop.sentinelle.domain.repository.BannedUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BannedUserRepositoryImpl(private val bannedUserDao: BannedUserDao) : BannedUserRepository {
    override fun getBannedUsersByAccount(accountId: Long): Flow<List<BannedUser>> = bannedUserDao.getBannedUsersByAccount(accountId)
        .map { list -> list.map { it.toDomain() } }.flowOn(Dispatchers.IO)

    override suspend fun banUser(user: BannedUser) = withContext(Dispatchers.IO) {
        bannedUserDao.insertBannedUser(user.toEntity())
    }

    override suspend fun unbanUser(platformUserId: String, accountId: Long) = withContext(Dispatchers.IO) {
        bannedUserDao.deleteBannedUser(platformUserId, accountId)
    }

    override suspend fun isUserBanned(platformUserId: String, accountId: Long): Boolean = withContext(Dispatchers.IO) {
        bannedUserDao.isUserBanned(platformUserId, accountId) > 0
    }
}
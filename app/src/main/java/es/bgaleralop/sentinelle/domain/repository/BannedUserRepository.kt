package es.bgaleralop.sentinelle.domain.repository

import es.bgaleralop.sentinelle.domain.model.BannedUser
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Contract to the strict control of trolls and automatic/manual banned.
 */
interface BannedUserRepository {
    fun getBannedUserByAccount(accountId: Long): Flow<List<BannedUser>>
    suspend fun banUser(user: BannedUser)
    suspend fun unbanUser(platformUserId: String, accountId: Long)
    suspend fun isUserBanned(platformUserId: String, accountId: Long): Boolean
}
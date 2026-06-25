package es.bgaleralop.sentinelle.domain.repository

import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Interface for the user repository.
 */
interface UserRepository {
    fun getUserTier(): Flow<UserTier>
    suspend fun setUserTier(tier: UserTier)
}
package es.bgaleralop.sentinelle.data.repository

import es.bgaleralop.sentinelle.data.local.security.EncryptedPreferencesManager
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val prefsManager: EncryptedPreferencesManager
) : UserRepository {
    override fun getUserTier(): Flow<UserTier> {
        return prefsManager.observeUserTier().flowOn(Dispatchers.IO)
    }

    override suspend fun setUserTier(tier: UserTier) {
        withContext(Dispatchers.IO) {
            return@withContext prefsManager.setUserTier(tier)
        }
    }
}
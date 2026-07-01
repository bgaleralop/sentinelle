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
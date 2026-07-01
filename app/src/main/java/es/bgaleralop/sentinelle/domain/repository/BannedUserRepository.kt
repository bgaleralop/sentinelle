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

import es.bgaleralop.sentinelle.domain.model.BannedUser
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Contract to the strict control of trolls and automatic/manual banned.
 */
interface BannedUserRepository {
    fun getBannedUsersByAccount(accountId: Long): Flow<List<BannedUser>>
    suspend fun banUser(user: BannedUser)
    suspend fun unbanUser(platformUserId: String, accountId: Long)
    suspend fun isUserBanned(platformUserId: String, accountId: Long): Boolean
}
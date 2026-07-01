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

package es.bgaleralop.sentinelle.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.bgaleralop.sentinelle.data.local.entity.BannedUserEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Data access object for the banned users table.
 */
@Dao
interface BannedUserDao {
    @Query("SELECT * FROM banned_users WHERE accountId = :accountId ORDER BY bannedAt DESC")
    fun getBannedUsersByAccount(accountId: Long): Flow<List<BannedUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBannedUser(user: BannedUserEntity)

    @Query("DELETE FROM banned_users WHERE platformUserId = :platformUserId AND accountId = :accountId")
    suspend fun deleteBannedUser(platformUserId: String, accountId: Long)

    @Query("SELECT COUNT(*) FROM banned_users WHERE platformUserId = :platformUserId AND accountId = :accountId")
    suspend fun isUserBanned(platformUserId: String, accountId: Long): Int
}
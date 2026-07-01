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
import es.bgaleralop.sentinelle.data.local.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 23-06-2026
 *
 * Data access object for the account table.
 */
@Dao
interface AccountDao {
    @Query("SELECT * FROM user_accounts WHERE isActive = 1")
    fun getActiveAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM user_accounts WHERE id = :id")
    suspend fun getAccountById(id: Long): AccountEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAccount(account: AccountEntity)

    @Query("DELETE FROM user_accounts WHERE id = :accountId")
    suspend fun deleteAccount(accountId: Long)

    @Query("SELECT COUNT(*) FROM user_accounts")
    suspend fun getAccountCount(): Int
}
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
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.bgaleralop.sentinelle.data.local.entity.BlacklistEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolome Galera López (bgalera)
 * @date 23-06-2026
 *
 * Data access object for the blacklist table.
 */
@Dao
interface BlacklistDao {
    // Retorna un Flow. Room emitirá una nueva lista automáticamente cada vez que la tabla cambie.
    @Query("SELECT * FROM blacklist_words ORDER BY word ASC")
    fun getAllWords(): Flow<List<BlacklistEntity>>

    @Query("SELECT * FROM blacklist_words WHERE accountId = :accountId")
    fun getBlacklistByAccount(accountId: Long): Flow<List<BlacklistEntity>>

    // Operación asíncrona (suspend) para no bloquear el hilo principal.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(entity: BlacklistEntity)

    @Delete
    suspend fun deleteWord(entity: BlacklistEntity)
}
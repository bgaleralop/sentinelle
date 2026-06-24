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

    // Operación asíncrona (suspend) para no bloquear el hilo principal.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(entity: BlacklistEntity)

    @Delete
    suspend fun deleteWord(entity: BlacklistEntity)
}
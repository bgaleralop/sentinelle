package es.bgaleralop.sentinelle.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Bartolome Galera López (bgalera)
 * @date 23-06-2026
 *
 * Entity for the blacklist table.
 */
@Entity(tableName = "blacklist_words")
data class BlacklistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val word: String
)
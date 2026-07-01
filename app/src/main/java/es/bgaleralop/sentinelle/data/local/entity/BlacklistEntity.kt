package es.bgaleralop.sentinelle.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author Bartolome Galera López (bgalera)
 * @date 23-06-2026
 *
 * Entity for the blacklist table.
 */
@Entity(
    tableName = "blacklist_words",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["accountId"])]
)
data class BlacklistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val accountId: Long,
    val word: String,
    val action: String,
)
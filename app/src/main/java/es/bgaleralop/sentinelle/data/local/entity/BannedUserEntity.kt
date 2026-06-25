package es.bgaleralop.sentinelle.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Entity class that represents a banned user in our database.
 */
@Entity(
    tableName = "banned_users",
    primaryKeys = ["platformUserId", "accountId"],
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
data class BannedUserEntity(
    val platformUserId: String,
    val accountId: Long,
    val username: String,
    val bannedAt: Long,
    val reason: String
)

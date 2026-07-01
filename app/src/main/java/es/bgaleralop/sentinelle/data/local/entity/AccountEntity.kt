package es.bgaleralop.sentinelle.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Entity class that represents an account in our database.
 */
@Entity(tableName = "user_accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val platform: String,
    val accountHandle: String,
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
    val isActive: Boolean
)
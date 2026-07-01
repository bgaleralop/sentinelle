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
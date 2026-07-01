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

package es.bgaleralop.sentinelle.domain.model

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Audit register. Each processed comment by the engine leave this historic.
 */
data class ModerationLog(
    val id: Long = 0,
    val accountId: Long,
    val commentId: String,
    val authorUsername: String,
    val commentText: String,
    val timestamp: Long,
    val isSpam: Boolean,
    val matchedWord: String?,
    val actionTaken: String, // "NONE", "DELETED", "HIDDEN", "RESTORED"
)

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
 * Model that represent the trolls banned locally by the user at an account community.
 */
data class BannedUser(
    val platformUserId: String, // ID único real de la plataforma.
    val accountId: Long, // En que cuenta del creador está betado.
    val username: String, // Su @handle visible.
    val bannedAt: Long,  //Timestamp del banneo.
    val reason: String,  // Motivo o palabra que disparó el ban.
    val actionTakenBySystem: Boolean = true //Si la acción fue tomada por el sistema (true) o por el usuario (false)
)

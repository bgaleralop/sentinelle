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

import es.bgaleralop.sentinelle.domain.model.enums.Platform

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Represent an account of the user in one of the different platforms supported by the app.
 */
data class SentinelleAccount(
    val platform: Platform, // "INSTAGRAM"  o "TIKTOK"
    val accountHandle: String, // Ej: @mi_usuario
    val accessToken: String, // Token de acceso (viajará cifrado en Data)
    val refreshToken: String, // Token de refresco (viajará cifrado en Data)
    val accessTokenExpiration: Long, // Fecha de expiración del token de acceso
    val refreshTokenExpiration: Long, // Fecha de expiración del token de refresco
    val id: Long = 0,
    val isActive: Boolean = true
)

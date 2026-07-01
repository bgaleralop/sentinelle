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

import es.bgaleralop.sentinelle.domain.model.enums.BlacklistAction

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 23-06-2026
 *
 * Data class that represent a word in our blacklist
 */
data class Word(
    val id: Long = 0,
    val accountId: Long,
    val word: String,
    val action: BlacklistAction
)

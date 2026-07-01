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

package es.bgaleralop.sentinelle.utils

import es.bgaleralop.sentinelle.domain.model.enums.UserTier

fun getMaxAccountsLimit(tier: UserTier) = when(tier) {
    UserTier.FREE -> 1
    UserTier.PRO -> 5
    UserTier.ENTERPRISE -> 50
}

fun isMaxAccountsReached(tier: UserTier, currentAccounts: Int) = currentAccounts >= getMaxAccountsLimit(tier)
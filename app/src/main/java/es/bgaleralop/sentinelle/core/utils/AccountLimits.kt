package es.bgaleralop.sentinelle.core.utils

import es.bgaleralop.sentinelle.domain.model.enums.UserTier

fun getMaxAccountsLimit(tier: UserTier) = when (tier) {
    UserTier.FREE -> 1
    UserTier.PRO -> 5
    UserTier.ENTERPRISE -> 50
}

fun isMaxAccountsReached(tier: UserTier, currentAccounts: Int) =
    currentAccounts >= getMaxAccountsLimit(tier)
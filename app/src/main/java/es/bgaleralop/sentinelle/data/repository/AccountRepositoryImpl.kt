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

package es.bgaleralop.sentinelle.data.repository

import es.bgaleralop.sentinelle.data.local.dao.AccountDao
import es.bgaleralop.sentinelle.data.local.mappers.toDomain
import es.bgaleralop.sentinelle.data.local.mappers.toEntity
import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import es.bgaleralop.sentinelle.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * Implementation of AccountRepository to access room database.
 */
class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
) : AccountRepository {
    override fun getActiveAccounts(): Flow<List<SentinelleAccount>> {
        return accountDao.getActiveAccounts().map { list -> list.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getAccountById(id: Long): SentinelleAccount? =
        withContext(Dispatchers.IO) {
            return@withContext accountDao.getAccountById(id)?.toDomain()
        }


    override suspend fun addAccount(account: SentinelleAccount): Result<Unit> =
        withContext(Dispatchers.IO) {
            runCatching { accountDao.insertAccount(account.toEntity()) }
        }

    override suspend fun deleteAccount(accountId: Long) = withContext(Dispatchers.IO) {
        accountDao.deleteAccount(accountId)
    }

    override suspend fun getAccountCount(): Int = withContext(Dispatchers.IO) {
        accountDao.getAccountCount()
    }
}
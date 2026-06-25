package es.bgaleralop.sentinelle.domain.repository

import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Contract to manage the multi-count support.
 */
interface AccountRepository {
    fun getActiveAccounts(): Flow<List<SentinelleAccount>>
    suspend fun getAccountById(id: Long): SentinelleAccount?
    suspend fun addAccount(account: SentinelleAccount): Result<Unit>
    suspend fun deleteAccount(accountId: Long)
    suspend fun getAccountCount(): Int
}
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

import es.bgaleralop.sentinelle.data.local.dao.BlacklistDao
import es.bgaleralop.sentinelle.data.local.mappers.toDomain
import es.bgaleralop.sentinelle.data.local.mappers.toEntity
import es.bgaleralop.sentinelle.data.local.mappers.toNewEntity
import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 23-06-2026
 *
 * Implementation of our WordRepository.
 */
class WordLocalImpl @Inject constructor(
    private val repository: BlacklistDao
) : WordRepository {
    override fun getBlacklist(): Flow<List<Word>> {
        return repository.getAllWords().map { entity -> entity.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)
    }

    override fun getBlacklistByAccount(accountId: Long): Flow<List<Word>> {
        return repository.getBlacklistByAccount(accountId)
            .map { list -> list.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun addWord(word: Word): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching { repository.insertWord(word.toNewEntity()) }
    }

    override suspend fun deleteWord(word: Word) {
        withContext(Dispatchers.IO) {
            repository.deleteWord(word.toEntity())
        }
    }
}
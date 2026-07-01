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

package es.bgaleralop.sentinelle.domain.repository

import es.bgaleralop.sentinelle.domain.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 23-06-2026
 *
 * Interface that contains the access contract to our WordRepository.
 */
interface WordRepository {
    fun getBlacklist(): Flow<List<Word>>
    fun getBlacklistByAccount(accountId: Long): Flow<List<Word>>
    suspend fun addWord(word: Word): Result<Unit>
    suspend fun deleteWord(word: Word)
}
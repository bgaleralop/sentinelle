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
    suspend fun addWord(word: Word)
    suspend fun deleteWord(word: Word)
}
package es.bgaleralop.sentinelle.data.repository

import es.bgaleralop.sentinelle.data.local.dao.BlacklistDao
import es.bgaleralop.sentinelle.data.local.mappers.toDomain
import es.bgaleralop.sentinelle.data.local.mappers.toEntity
import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WordLocalImpl(
    private val repository: BlacklistDao
) : WordRepository {
    override fun getBlacklist(): Flow<List<Word>> {
        return repository.getAllWords().map { entity -> entity.map { it.toDomain() } }
    }

    override suspend fun addWord(word: Word) {
        repository.insertWord(word.toEntity())
    }

    override suspend fun deleteWord(word: Word) {
        repository.deleteWord(word.toEntity())
    }
}
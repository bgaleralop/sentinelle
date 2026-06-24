package es.bgaleralop.sentinelle.domain.usecase

import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.repository.WordRepository

class DeleteWordUseCase(
    private val repository: WordRepository
) {
    suspend operator fun invoke(word: Word): Result<Word> {
        try {
            repository.deleteWord(word)
            return Result.success(word)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
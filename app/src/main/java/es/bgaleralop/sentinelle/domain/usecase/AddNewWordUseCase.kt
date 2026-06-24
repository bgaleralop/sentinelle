package es.bgaleralop.sentinelle.domain.usecase

import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.repository.WordRepository
import es.bgaleralop.sentinelle.domain.util.Normalizer

class AddNewWordUseCase(
    private val repository: WordRepository
) {
    suspend operator fun invoke(newWord: String): Result<Word> {
        try {
            repository.addWord(
                Word(
                    id = 0,
                    word = Normalizer.normalizeWord(newWord)
                )
            )

            return Result.success(Word(id = 0, word = newWord))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
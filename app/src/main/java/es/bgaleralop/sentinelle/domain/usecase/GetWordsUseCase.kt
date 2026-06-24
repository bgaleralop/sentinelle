package es.bgaleralop.sentinelle.domain.usecase

import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class GetWordsUseCase(
    private val repository: WordRepository
) {
    operator fun invoke(): Flow<List<Word>>{
        return repository.getBlacklist()
    }
}
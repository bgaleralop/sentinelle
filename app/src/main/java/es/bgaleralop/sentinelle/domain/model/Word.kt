package es.bgaleralop.sentinelle.domain.model

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 23-06-2026
 *
 * Data class that represent a word in our blacklist
 */
data class Word(
    val id: Long = 0,
    val accountId: Long,
    val word: String,
    val action: BlacklistAction
)

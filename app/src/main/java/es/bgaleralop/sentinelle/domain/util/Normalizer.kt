package es.bgaleralop.sentinelle.domain.util

object Normalizer {
    private val avoidedCharacters: String = ""
    fun normalizeWord(word: String): String {
        return word.lowercase().trim()
    }
}
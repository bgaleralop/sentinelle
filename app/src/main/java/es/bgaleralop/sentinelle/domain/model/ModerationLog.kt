package es.bgaleralop.sentinelle.domain.model

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Audit register. Each processed comment by the engine leave this historic.
 */
data class ModerationLog(
    val id: Long = 0,
    val accountId: Long,
    val commentId: String,
    val authorUsername: String,
    val commentText: String,
    val timestamp: Long,
    val isSpam: Boolean,
    val matchedWord: String?,
    val actionTaken: String, // "NONE", "DELETED", "HIDDEN", "RESTORED"
)

package es.bgaleralop.sentinelle.domain.model

data class DetailedCommentLog(
    val log: ModerationLog,
    val account: SentinelleAccount,
    val matchedWord: String?
)

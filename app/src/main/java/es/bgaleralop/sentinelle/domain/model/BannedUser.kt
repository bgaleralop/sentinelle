package es.bgaleralop.sentinelle.domain.model

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Model that represent the trolls banned locally by the user at an account community.
 */
data class BannedUser(
    val platformUserId: String, // ID único real de la plataforma.
    val accountId: Long, // En que cuenta del creador está betado.
    val username: String, // Su @handle visible.
    val bannedAt: Long,  //Timestamp del banneo.
    val reason: String,  // Motivo o palabra que disparó el ban.
    val actionTakenBySystem: Boolean = true //Si la acción fue tomada por el sistema (true) o por el usuario (false)
)

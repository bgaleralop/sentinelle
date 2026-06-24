package es.bgaleralop.sentinelle.domain.model

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Represent an account of the user in one of the different platforms supported by the app.
 */
data class SentinelleAccount(
    val platform: String, // "INSTAGRAM"  o "TIKTOK"
    val handle: String, // Ej: @mi_usuario
    val token: String, // Token de acceso (viajará cifrado en Data)
    val id: Long = 0,
    val isActive: Boolean = true
)

package es.bgaleralop.sentinelle.domain.model.enums

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Enumeration that represent the different actions that the user can take when
 * there is a coincidence with a blacklist word.
 */
enum class BlacklistAction {
    DELETE, // Borrado fulminante de la API
    HIDE // Ocultar comentario (Shadow ban / Solo disponible en Pro/Enterprise)
}
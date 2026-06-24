package es.bgaleralop.sentinelle.data.local.mappers

import es.bgaleralop.sentinelle.data.local.entity.BlacklistEntity
import es.bgaleralop.sentinelle.domain.model.Word

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 23-06-2026
 *
 * File that contains the different mappers between our local repository entities and our domain classes.
 */
fun BlacklistEntity.toDomain(): Word = Word(
    id = id,
    word = word
)

fun Word.toEntity(): BlacklistEntity = BlacklistEntity(
    word = word
)

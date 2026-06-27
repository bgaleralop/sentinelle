package es.bgaleralop.sentinelle.domain.model

data class SparklinePoint(
    val hour: Int, // 0 a 23
    val count: Int // Cantidad de comentarios analizados en esa hora.
)

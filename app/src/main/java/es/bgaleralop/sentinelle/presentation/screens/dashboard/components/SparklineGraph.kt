package es.bgaleralop.sentinelle.presentation.screens.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.bgaleralop.sentinelle.domain.model.SparklinePoint

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 25-06-2026
 *
 * Composable que renderiza un Sparkline.
 */
@Composable
fun SparklineGraph(
    points: List<SparklinePoint>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0x00, 0xC9, 0xA7), // Tu Seafoam Teal de diseño (#00C9A7)
    lineWidth: Dp = 2.5.dp
) {
    // Validamos que existan suficientes puntos para trazar una línea
    if (points.size < 2) return

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        // Encontramos los extremos para escalar correctamente la UI sin desbordamientos
        val maxCount = points.maxOf { it.count }.toFloat().coerceAtLeast(1f)
        // Fijamos el mínimo en 0 para que la gráfica tenga un suelo realista
        val minCount = 0f

        // Distancia horizontal idéntica entre cada nodo del gráfico
        val distanceX = width / (points.size - 1)

        // Trazado de la línea de tendencia principal
        val strokePath = Path().apply {
            points.forEachIndexed { index, point ->
                val x = index * distanceX

                // Mapeo normalizado: (valor / max) * altura disponible
                // Invertimos el eje Y restándoselo a 'height' porque en Canvas el (0,0) es la esquina superior izquierda
                val fractionY = (point.count - minCount) / (maxCount - minCount)
                val y = height - (fractionY * height)

                if (index == 0) {
                    moveTo(x, y)
                } else {
                    lineTo(x, y)
                }
            }
        }

        // Trazado del área de relleno para el degradado inferior
        val fillPath = Path().apply {
            addPath(strokePath)
            // Cerramos la geometría tirando líneas hacia las esquinas inferiores del Canvas
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }

        // 1. Dibujamos primero el degradado de fondo para que la línea quede por encima
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    lineColor.copy(alpha = 0.25f), // Opaco arriba, justo debajo de la línea
                    Color.Transparent             // Se desvanece por completo al llegar al suelo
                ),
                startY = 0f,
                endY = height
            )
        )

        // 2. Dibujamos la línea estilizada con bordes redondeados
        drawPath(
            path = strokePath,
            color = lineColor,
            style = Stroke(
                width = lineWidth.toPx(),
                cap = StrokeCap.Round,       // Extremos limpios y redondeados
                join = StrokeJoin.Round      // Suaviza los vértices angulares de los picos
            )
        )
    }
}
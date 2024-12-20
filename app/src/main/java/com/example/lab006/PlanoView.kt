package com.example.lab006

import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.lab006.fragments.*

class PlanoView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paintFill = Paint()  // Para el relleno crema
    private val paintStroke = Paint() // Para el borde
    private val paintText = Paint() // Para letras
    private val paintDoor = Paint()   // Para la puerta (línea naranja)
    private val ambientes = mutableListOf<AmbientePoligonal>()
    private val rectanguloPrincipal = RectanguloPrincipal(0f, 0f, 620f, 969f)  // Definir el rectángulo principal

    init {
        paintFill.isAntiAlias = true
        paintFill.style = Paint.Style.FILL
        paintFill.color = Color.parseColor("#FFFDD0")  // Color de relleno crema

        paintText.isAntiAlias = true
        paintText.textSize = 10f  // Tamaño de texto reducido
        paintText.color = Color.parseColor("#8B4513") // Color café para el texto

        paintStroke.style = Paint.Style.STROKE
        paintStroke.isAntiAlias = true
        paintStroke.strokeWidth = 7f  // Grosor del borde
        paintStroke.color = Color.parseColor("#8B4513")  // Borde negro para todos los ambientes

        paintDoor.isAntiAlias = true
        paintDoor.strokeWidth = 5f  // Grosor de la línea para la puerta
        paintDoor.color = Color.parseColor("#FF0000")  // Color de la puerta en roja

        // Definir los ambientes con las coordenadas ajustadas
        ambientes.add(
            AmbientePoligonal("Entrada", listOf(
                PointF(518f, 878f), PointF(561f, 878f),PointF(561f, 894f),
                PointF(620f, 894f), PointF(620f, 932f),PointF(518f, 932f)
            ))
        )

        ambientes.add(
            AmbientePoligonal("Patio", listOf(
                PointF(187f, 841f), PointF(283f, 841f), PointF(283f, 878f),
                PointF(518f, 878f), PointF(518f, 932f), PointF(374f, 932f),
                PointF(374f, 894f),
                PointF(283f, 894f), PointF(283f, 932f), PointF(187f, 932f)
            ))
        )

        ambientes.add(
            AmbientePoligonal("Claustro Novicio", listOf(
                PointF(53f, 819f), PointF(150f, 819f), PointF(150f, 921f), PointF(53f, 921f)
            ))
        )

        ambientes.add(
            AmbientePoligonal("Claustro Naranjo", listOf(
                PointF(283f, 691f), PointF(374f, 691f), PointF(374f, 809f), PointF(283f, 809f)
            ))
        )

        ambientes.add(
            AmbientePoligonal("Coro", listOf(
                PointF(561f, 771f), PointF(620f, 771f), PointF(620f, 894f), PointF(561f, 894f)
            ))
        )

        ambientes.add(
            AmbientePoligonal("Iglesia", listOf(
                PointF(561f, 584f), PointF(620f, 584f), PointF(620f, 771f), PointF(561f, 771f)
            ))
        )

        ambientes.add(
            AmbientePoligonal("Claustro Mayor", listOf(
                PointF(428f, 584f), PointF(561f, 584f), PointF(561f, 809f), PointF(428f, 809f)
            ))
        )

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val startX = 50f
        val startY = 50f

        // Dibujo del rectángulo principal
        val rectPath = Path()
        rectPath.moveTo(startX, startY)
        rectPath.lineTo(startX + rectanguloPrincipal.width, startY)
        rectPath.lineTo(startX + rectanguloPrincipal.width, startY + rectanguloPrincipal.height)
        rectPath.lineTo(startX, startY + rectanguloPrincipal.height)
        rectPath.close()

       canvas.drawPath(rectPath, paintStroke)
       canvas.drawPath(rectPath, paintFill)

        // Dibujo de los ambientes
        for (ambiente in ambientes) {
            val path = Path()
            val firstPoint = ambiente.puntos.first()
            path.moveTo(startX + firstPoint.x, startY + firstPoint.y)

            for (point in ambiente.puntos.drop(1)) {
                path.lineTo(startX + point.x, startY + point.y)
            }
            path.close()

            canvas.drawPath(path, paintFill)
            canvas.drawPath(path, paintStroke)

            // Dibuja el nombre del ambiente
            val centerX = ambiente.puntos.map { it.x }.average().toFloat() + startX
            val centerY = ambiente.puntos.map { it.y }.average().toFloat() + startY
            paintText.color = Color.BLACK
            paintText.textSize = 30f
            canvas.drawText(ambiente.nombre, centerX - paintText.measureText(ambiente.nombre) / 2, centerY, paintText)

            // Dibuja la puerta
            val puertaX = startX + firstPoint.x
            val puertaY = startY + firstPoint.y
            canvas.drawLine(puertaX, puertaY, puertaX + 50f, puertaY, paintDoor)
        }

        // Dibujo del polígono adicional con las coordenadas especificadas
        val extraPasillo = Paint().apply {
            color = Color.parseColor("#00000000")  // Color rojo
            style = Paint.Style.FILL // Rellenar el interior del polígono
        }
        val coordinates = arrayOf(
            floatArrayOf(294f, 0f), floatArrayOf(310f, 0f), floatArrayOf(310f, 157f),
            floatArrayOf(465f, 157f), floatArrayOf(406f, 0f), floatArrayOf(417f, 0f),
            floatArrayOf(481f, 157f), floatArrayOf(545f, 157f), floatArrayOf(545f, 167f),
            floatArrayOf(508f, 167f), floatArrayOf(508f, 205f), floatArrayOf(535f, 215f),
            floatArrayOf(535f, 263f), floatArrayOf(561f, 263f), floatArrayOf(561f, 338f),
            floatArrayOf(545f, 349f), floatArrayOf(470f, 247f), floatArrayOf(470f, 167f),
            floatArrayOf(310f, 167f), floatArrayOf(321f, 312f), floatArrayOf(347f, 312f),
            floatArrayOf(347f, 338f), floatArrayOf(374f, 338f), floatArrayOf(385f, 397f),
            floatArrayOf(428f, 381f), floatArrayOf(524f, 328f), floatArrayOf(535f, 349f),
            floatArrayOf(518f, 354f), floatArrayOf(518f, 429f), floatArrayOf(551f, 435f),
            floatArrayOf(551f, 579f), floatArrayOf(535f, 579f), floatArrayOf(535f, 451f),
            floatArrayOf(481f, 435f), floatArrayOf(470f, 402f), floatArrayOf(385f, 413f),
            floatArrayOf(385f, 595f), floatArrayOf(428f, 595f), floatArrayOf(428f, 584f),
            floatArrayOf(385f, 584f), floatArrayOf(385f, 680f), floatArrayOf(363f, 680f),
            floatArrayOf(363f, 579f), floatArrayOf(347f, 579f), floatArrayOf(347f, 445f),
            floatArrayOf(342f, 424f), floatArrayOf(342f, 370f),floatArrayOf(294f, 370f)
        )
        val nMonasterio = arrayOf(
            floatArrayOf(0f,328f),floatArrayOf(294f,328f),floatArrayOf(294f,370f),
            floatArrayOf(342f,370f),floatArrayOf(342f,424f),floatArrayOf(347f,445f),
            floatArrayOf(347f,579f),floatArrayOf(241f,595f),floatArrayOf(241f,675f),
            floatArrayOf(171f,675f),floatArrayOf(171f,595f),floatArrayOf(123f,595f),
            floatArrayOf(123f,632f),floatArrayOf(0f,632f)

        )
        val extraPasilloPath = Path().apply {
            moveTo(startX + coordinates[0][0], startY + coordinates[0][1])
            coordinates.forEach { lineTo(startX + it[0], startY + it[1]) }
            close()
        }
        val extraMonasterioPath = Path().apply {
            moveTo(startX + nMonasterio[0][0], startY + nMonasterio[0][1])
            nMonasterio.forEach { lineTo(startX + it[0], startY + it[1]) }
            close()
        }
        canvas.drawPath(extraPasilloPath, extraPasillo)
        canvas.drawPath(extraPasilloPath, paintStroke)

        canvas.drawPath(extraMonasterioPath, extraPasillo)
        canvas.drawPath(extraMonasterioPath, paintStroke)
        val circleX = startX+482f
        val circleY = startY+382f
        val circleRadius = 12f
        canvas.drawCircle(circleX, circleY, circleRadius, paintStroke)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                for (ambiente in ambientes) {
                    if (isPointInPolygon(PointF(x, y), ambiente.puntos)) {
                        onAmbienteClick(ambiente)
                        return true
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun onAmbienteClick(ambiente: AmbientePoligonal) {
        val fragment = when (ambiente.nombre) {
            "Entrada" -> Ambiente1Fragment()
            "Patio" -> Ambiente2Fragment()
            "Claustro Novicio" -> Ambiente3Fragment()
            "Claustro Naranjo" -> Ambiente4Fragment()
            "Coro" -> Ambiente5Fragment()
            "Iglesia" -> Ambiente5Fragment() // Reemplazar con el fragmento correcto si es necesario
            "Claustro Mayor" -> Ambiente5Fragment() // Reemplazar con el fragmento correcto si es necesario
            else -> throw IllegalArgumentException("Ambiente desconocido")
        }

        val activity = context as FragmentActivity
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun isPointInPolygon(point: PointF, polygon: List<PointF>): Boolean {
        var intersections = 0
        for (i in polygon.indices) {
            val p1 = polygon[i]
            val p2 = polygon[(i + 1) % polygon.size]
            if (((p1.y > point.y) != (p2.y > point.y)) &&
                (point.x < (p2.x - p1.x) * (point.y - p1.y) / (p2.y - p1.y) + p1.x)) {
                intersections++
            }
        }
        return intersections % 2 != 0
    }

    data class RectanguloPrincipal(val x: Float, val y: Float, val width: Float, val height: Float)
    data class AmbientePoligonal(val nombre: String, val puntos: List<PointF>)
}

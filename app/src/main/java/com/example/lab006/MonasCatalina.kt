package com.example.lab006
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MonasCatalina @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibujar área principal del monasterio
        paint.color = Color.parseColor("#FFDEAD") // Color arena para paredes
        canvas.drawRect(50f, 50f, 900f, 1600f, paint) // Área general

        // Dibujar habitaciones o sectores del monasterio
        paint.color = Color.parseColor("#FFB6C1") // Color rosado para áreas específicas

        // Ejemplo: sector 1
        canvas.drawRect(100f, 100f, 300f, 300f, paint)
        canvas.drawText("Sala de recepción", 110f, 150f, textPaint)

        // Ejemplo: sector 2
        canvas.drawRect(350f, 100f, 550f, 300f, paint)
        canvas.drawText("Celdas de monjas", 360f, 150f, textPaint)

        // Ejemplo: sector 3
        paint.color = Color.parseColor("#ADD8E6") // Color azul claro para otro sector
        canvas.drawRect(100f, 350f, 550f, 550f, paint)
        canvas.drawText("Cocina", 110f, 400f, textPaint)

        // Ejemplo: sector 4 (pasillo)
        paint.color = Color.parseColor("#D3D3D3") // Color gris para caminos
        canvas.drawRect(200f, 600f, 700f, 650f, paint)
        canvas.drawText("Pasillo", 210f, 630f, textPaint)

        // Etiquetas adicionales para otras áreas
        textPaint.color = Color.BLACK
        canvas.drawText("Patio Principal", 110f, 800f, textPaint)
        canvas.drawText("Capilla", 400f, 1100f, textPaint)
    }
}

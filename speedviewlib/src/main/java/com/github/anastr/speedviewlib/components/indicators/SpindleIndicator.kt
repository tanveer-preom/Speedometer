package com.github.anastr.speedviewlib.components.indicators

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Path
import android.util.DisplayMetrics
import java.security.AccessController.getContext


/**
 * this Library build By Anas Altair
 * see it on [GitHub](https://github.com/anastr/SpeedView)
 */
class SpindleIndicator(val context: Context) : Indicator<SpindleIndicator>(context) {

    private val indicatorPath = Path()

    init {
        width = dpTOpx(16f)
    }

    override fun getTop(): Float {
        return getViewSize() * .18f + speedometer!!.padding
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(getCenterX(), speedometer!!.padding.toFloat() + speedometer!!.indicatorPadding, dpTOpx(4f), indicatorPaint)
    }

    override fun updateIndicator() {
        indicatorPath.reset()
        indicatorPaint.color = color
    }

    override fun setWithEffects(withEffects: Boolean) {
        if (withEffects && !speedometer!!.isInEditMode) {
            indicatorPaint.maskFilter = BlurMaskFilter(15f, BlurMaskFilter.Blur.SOLID)
        } else {
            indicatorPaint.maskFilter = null
        }
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
}

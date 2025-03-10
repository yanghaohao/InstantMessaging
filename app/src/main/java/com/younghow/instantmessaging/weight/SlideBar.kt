package com.younghow.instantmessaging.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.younghow.instantmessaging.R
import com.example.commen.extentions.sp

class SlideBar(
    context: Context,
    attributeSet: AttributeSet? = null,
) : View(context, attributeSet) {
    private var sectionHeight = 0f
    private val paint = Paint()
    private var textBaseLine = 0f
    var onSectionChange: onSectionChangeLisenter? = null

    companion object {
        private val SECTIONS =
            arrayOf(
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z",
            )
    }

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = 12.sp(context).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(
        w: Int,
        h: Int,
        oldw: Int,
        oldh: Int,
    ) {
        super.onSizeChanged(w, h, oldw, oldh)
        sectionHeight = h * 1.0f / SECTIONS.size

        val fontMetrics = paint.fontMetrics
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        textBaseLine = sectionHeight / 2 + (textHeight / 2 - fontMetrics.descent)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = width * 1.0f / 2
        var baseLine = textBaseLine
        SECTIONS.forEach {
            canvas.drawText(it, x, baseLine, paint)
            baseLine += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                val index = getTouchIndex(event)
                val firstLetter = SECTIONS[index]
                onSectionChange!!.onSectionChange(firstLetter)
            }

            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                onSectionChange!!.onSlideFinish()
            }
        }
        return true
    }

    private fun getTouchIndex(event: MotionEvent): Int {
        var index = (event.y / sectionHeight).toInt()
        if (index < 0) {
            index = 0
        } else if (index >= SECTIONS.size) {
            index = SECTIONS.size - 1
        }
        return index
    }

    interface onSectionChangeLisenter {
        fun onSectionChange(firstLetter: String)

        fun onSlideFinish()
    }
}

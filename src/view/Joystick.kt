
package com.myapp.RemoteControlJoystick.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.math.min

class Joystick @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    /* drawing the joystick */
    //internal circle - the joystick
    private val joystickPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        isAntiAlias = true
    }
    //external circle - the borders
    private val borderCirclePaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.DKGRAY
        isAntiAlias = true
    }

    //init components
    private var joystickRadius: Float = 0f
    private var joystickCenter: PointF = PointF()
    private var baseRadius: Float = 0f
    private var baseCenter: PointF = PointF()
    private var aileron: Float = 0f
    private var elevator: Float = 0f
    var onChange = fun(aileron: Float, elevator: Float): Boolean = null!!


    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        joystickRadius = 0.1f * min(width, height)
        joystickCenter = PointF(width / 2.0f, height / 2.0f)
        baseRadius = 0.3f * min(width, height)
        baseCenter = PointF(width / 2.0f, height / 2.0f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(baseCenter.x, baseCenter.y, baseRadius, joystickPaint)
        canvas.drawCircle(joystickCenter.x, joystickCenter.y, joystickRadius, borderCirclePaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> touchUp(event.x, event.y)
        }
        return true
    }

    private fun touchMove(x: Float, y: Float) {
        val distanceX = abs(x - baseCenter.x)
        val distanceY = abs(y - baseCenter.y)
        if ((baseRadius >= distanceX) && (baseRadius >= distanceY)) {
            joystickCenter.x = x;
            joystickCenter.y = y;

            aileron = (x - baseCenter.x) / (baseRadius)
            elevator = (y - baseCenter.y) / (baseRadius)

            //notify joystick values has changed
            onChange(aileron,elevator)
        }
        invalidate()
    }

    private fun touchUp(x: Float, y: Float) {
        joystickCenter.x = baseCenter.x
        joystickCenter.y = baseCenter.y
        invalidate()
    }
}

package ui.anwesome.com.keyholeview

/**
 * Created by anweshmishra on 25/04/18.
 */

import android.graphics.*
import android.content.Context
import android.view.View
import android.view.MotionEvent
import android.graphics.Paint.Cap

class KeyHoleView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var deg : Double = 0.0) {

        fun update(stopcb : () -> Unit) {
            deg += (Math.PI/20) * dir
            scale = Math.sin(deg).toFloat()
            if (deg > Math.PI) {
                deg = 0.0
                scale = 0f
                stopcb()
            }
        }

        fun startUpdating(startcb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f
                startcb()
            }
        }
    }

}
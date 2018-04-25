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
}
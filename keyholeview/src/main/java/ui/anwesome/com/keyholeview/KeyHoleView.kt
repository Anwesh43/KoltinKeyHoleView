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

    private val renderer : Renderer = Renderer(this)

    override fun onDraw(canvas : Canvas) {
        renderer.render(canvas, paint)
    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                renderer.handleTap()
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

    data class Animator (var view : View, var animated : Boolean = false) {

        fun animate(updatecb : () -> Unit) {
            if (animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch (ex : Exception) {

                }
            }
        }

        fun start() {
            if (!animated) {
                animated = true
                view.postInvalidate()
            }
        }

        fun stop() {
            if (animated) {
                animated = false
            }
        }
    }

    data class KeyHole (var i : Int, val state : State = State()) {

        fun draw(canvas : Canvas, paint : Paint) {
            val w : Float = canvas.width.toFloat()
            val h : Float = canvas.height.toFloat()
            val r : Float = Math.min(w, h)/3
            val path : Path = Path()
            val x : Float = r * Math.cos(250 * Math.PI/180).toFloat()
            val y : Float = r * Math.sin(290 * Math.PI/180).toFloat()
            canvas.save()
            canvas.translate(w/2, h/2)
            path.moveTo(x, y)
            path.lineTo(x, 0f)
            path.lineTo(-x, 0f)
            path.lineTo(-x, y)
            for (i in -70..250) {
                val a : Float = r * Math.cos(i * Math.PI/180).toFloat()
                val b : Float = r * Math.sin(i * Math.PI/180).toFloat()
                path.lineTo(a, b)
            }
            canvas.drawPath(path, paint)
            canvas.restore()
        }

        fun update(stopcb : () -> Unit) {
            state.update(stopcb)
        }

        fun startUpdating(startcb : () -> Unit) {
            state.startUpdating(startcb)
        }
    }

    data class Renderer(var view : KeyHoleView) {

        private val animator : Animator = Animator(view)

        private val keyHole : KeyHole = KeyHole(0)

        fun render(canvas : Canvas, paint : Paint) {
            canvas.drawColor(Color.parseColor("#212121"))
            paint.color = Color.parseColor("#D84315")
            keyHole.draw(canvas, paint)
            animator.animate {
                keyHole.update {
                    animator.stop()
                }
            }
        }

        fun handleTap() {
            keyHole.startUpdating {
                animator.start()
            }
        }
    }
}
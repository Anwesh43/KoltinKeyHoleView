package ui.anwesome.com.kotlinkeyholeview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.keyholeview.KeyHoleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KeyHoleView.create(this)
    }
}

package datatouch.uikitapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import datatouch.uikit.components.textview.Font
import datatouch.uikit.components.textview.TextView


class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btn = findViewById<View>(R.id.btn)
        val tv = findViewById<TextView>(R.id.tv)


        btn.setOnClickListener {
            tv.setTypeFace(Font.Bold)
        }

    }

}
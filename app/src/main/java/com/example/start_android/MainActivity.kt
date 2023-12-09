package com.example.start_android

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnOk)
        findViewById<Button>(R.id.btnCancel)

    }
    val tvOut = findViewById<TextView>(R.id.tvOut)
    override fun onClick(v: View) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        when (v.id) {
            R.id.btnOk -> {
                // кнопка ОК
                tvOut.text = "Нажата кнопка ОК"
            }
            R.id.btnCancel -> {
                // кнопка Cancel
                tvOut.text = "Нажата кнопка Cancel"
            }
        }
    }
}
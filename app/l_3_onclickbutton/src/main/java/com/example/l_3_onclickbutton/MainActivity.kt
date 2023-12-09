package com.example.l_3_onclickbutton

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnOk = findViewById<Button>(R.id.btnOk)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        val tvOut = findViewById<TextView>(R.id.tvOut)

        btnCancel.setOnClickListener {
            tvOut.text = "Cancel"
        }
        btnOk.setOnClickListener {
            tvOut.text = "Ok"
        }
    }
}
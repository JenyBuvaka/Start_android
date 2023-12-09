package com.example.l_22_activity_result

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class color : AppCompatActivity() {
    lateinit var btnRed:Button
    lateinit var btnGreen:Button
    lateinit var btnBlue:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        btnRed = findViewById(R.id.btnRed)
        btnBlue = findViewById(R.id.btnBlue)
        btnGreen = findViewById(R.id.btnGreen)

        val intent = Intent()
        btnGreen.setOnClickListener {
            intent.putExtra("color", Color.GREEN)
            setResult(RESULT_OK,intent)
            finish()
        }
        btnRed.setOnClickListener {
            intent.putExtra("color", Color.RED)
            setResult(RESULT_OK,intent)
            finish()
        }
        btnBlue.setOnClickListener {
            intent.putExtra("color", Color.BLUE)
            setResult(RESULT_OK,intent)
            finish()
        }
    }

}
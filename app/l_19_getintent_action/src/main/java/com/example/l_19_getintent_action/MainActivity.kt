package com.example.l_19_getintent_action

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnTime = findViewById<Button>(R.id.btnTime)
        val btnDate = findViewById<Button>(R.id.btnDate)

        btnTime.setOnClickListener(this)
        btnDate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent: Intent

        when(v?.id){
            R.id.btnDate->{
                intent = Intent("ru.startandroid.intent.action.showdate")
                startActivity(intent)
            }
            R.id.btnTime->{
                intent = Intent("ru.startandroid.intent.action.showtime")
                startActivity(intent)
            }
        }
    }
}
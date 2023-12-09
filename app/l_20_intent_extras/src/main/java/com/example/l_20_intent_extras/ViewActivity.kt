package com.example.l_20_intent_extras

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ViewActivity : AppCompatActivity() {
    lateinit var tvView:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        tvView = findViewById(R.id.tvView)

        val intent = intent
        val fName = intent.getStringExtra("fname")
        val lName = intent.getStringExtra("lname")
        tvView.text = "Your name is: $fName $lName"
    }
}
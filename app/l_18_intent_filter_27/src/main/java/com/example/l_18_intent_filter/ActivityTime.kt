package com.example.l_18_intent_filter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date

class ActivityTime: AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        val simpleDate = SimpleDateFormat("HH:mm:ss")
        var time = simpleDate.format(Date(System.currentTimeMillis()))

        val tvTime = findViewById<TextView>(R.id.tvTime)
        tvTime.text = time
    }
}
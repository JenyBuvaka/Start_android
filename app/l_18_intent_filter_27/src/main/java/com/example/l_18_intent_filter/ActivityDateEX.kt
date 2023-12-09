package com.example.l_18_intent_filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date

class ActivityDateEX : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        var simpleDateFormate = SimpleDateFormat("EEE,MMM d, yyyy")
        val date = simpleDateFormate.format(Date(System.currentTimeMillis()))

        val tvDate = findViewById<TextView>(R.id.tvDate)
        tvDate.text = date
    }
}
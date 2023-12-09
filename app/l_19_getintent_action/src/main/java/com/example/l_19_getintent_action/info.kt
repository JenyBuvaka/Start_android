package com.example.l_19_getintent_action

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date

class info: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)

        //Получаем Intent который вызвал это Activity
        val intent = intent
        //читаем из него action
        var action = intent.action

        var format = ""
        var textInfo = ""

        //В зависимости от action заполняем переменные
        if (action!!.equals("ru.startandroid.intent.action.showtime")){
            format = "HH:mm:ss"
            textInfo = "Time: "
        }
        else if (action!!.equals("ru.startandroid.intent.action.showdate")){
            format = "dd.MM.yyyy"
            textInfo = "Date: "
        }
        // в зависимости от содержимого переменной format
        // получаем дату или время в переменную datetime
        val simpleDateFormat = SimpleDateFormat(format)
        val dateTime = simpleDateFormat.format(Date(System.currentTimeMillis()))

        val tvDate = findViewById<TextView>(R.id.tvInfo)
        tvDate.setText(textInfo + dateTime)
    }
}
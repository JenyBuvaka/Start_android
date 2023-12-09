package com.example.l_45_time_picker_dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {

    private val DIALOG_TIME = 1
    private var myHour = 14
    private var myMinute = 35
    lateinit var tvTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTime = findViewById(R.id.tvTime)
        tvTime.setOnClickListener {
            showDialog(DIALOG_TIME)
        }
    }

     @SuppressLint("SetTextI18n")
     private val myCallBack = OnTimeSetListener{ view: TimePicker?, hourOfDay: Int, minute: Int ->
            myHour = hourOfDay
            myMinute = minute
            tvTime.text = "Time is $myHour hours $myMinute"
        }

    override fun onCreateDialog(id: Int): Dialog {
        if (id == DIALOG_TIME) {
            val tpd = TimePickerDialog(this, myCallBack, myHour, myMinute,false)
            return tpd
        }
        return super.onCreateDialog(id)
    }
}

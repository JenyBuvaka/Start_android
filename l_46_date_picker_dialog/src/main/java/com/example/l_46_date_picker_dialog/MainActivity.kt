package com.example.l_46_date_picker_dialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val DIALOG_DATE = 1
    private var myYear = 2011
    private var myMonth = 2
    private var myDay = 3
    lateinit var tvDate:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDate = findViewById(R.id.tvDate)
        tvDate.setOnClickListener {
            showDialog(DIALOG_DATE)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        if (id == DIALOG_DATE){
            val tpg = DatePickerDialog(this,myCallBack,myYear,myMonth,myDay)
            return tpg
        }
        return super.onCreateDialog(id)
    }

    @SuppressLint("SetTextI18n")
    val myCallBack = DatePickerDialog.OnDateSetListener { view: DatePicker, year:Int, monthOfYear:Int, dayOfMonth:Int->
        myYear = year
        myDay = dayOfMonth
        myMonth = monthOfYear
        tvDate.text = "Today is $myDay/$myMonth/$myYear"

    }
}
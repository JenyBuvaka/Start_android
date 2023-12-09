package com.example.l_48_alert_dialog_prepare

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "myLogs"
    private val  DIALOG = 1
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("HH:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            showDialog(DIALOG)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        Log.d(LOG_TAG, "onCreateDialog")
        if (id == DIALOG){
            val adb = AlertDialog.Builder(this)
            adb.setTitle("Текущее время")
            adb.setMessage(sdf.format(Date(System.currentTimeMillis())))
            return adb.create()
        }
        return super.onCreateDialog(id)
    }

    override fun onPrepareDialog(id: Int, dialog: Dialog?) {
        super.onPrepareDialog(id, dialog)
        Log.d(LOG_TAG, "onPrepareDialog")
        if (id==DIALOG){
            (dialog as AlertDialog).setMessage(sdf.format(Date(System.currentTimeMillis())))
        }
    }
}
package com.example.l_52_alert_dialog_operation

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    private val DIALOOG = 1

    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button1)
        btn.setOnClickListener {
            showDialog(DIALOOG)

            val h = Handler()

            h.postDelayed({
                kotlin.run { method1() }
            },2000)

            h.postDelayed({kotlin.run { method2()} },4000)
        }
    }
    fun method1(){
        dialog.dismiss()
    }
    fun method2(){
dialog.show()
    }
    //У объекта Dialog есть еще метод show. Чем он отличается от метода Activity showDialog? show просто покажет созданный диалог, а showDialog, начинает проверять был ли уже создан диалог, создает его, если необходимо, и вызывает для него метод onPrepareDialo

    override fun onCreateDialog(id: Int): Dialog {
        if (id==DIALOOG){
            Log.d(LOG_TAG,"Create")
            val adb = AlertDialog.Builder(this)
            adb.setTitle("Title")
            adb.setMessage("Message")
            adb.setPositiveButton("OK",null)
            dialog = adb.create()

            dialog.setOnShowListener {
                Log.d(LOG_TAG,"Show")
            }
            dialog.setOnCancelListener{
                Log.d(LOG_TAG,"Cancel")
            }
            dialog.setOnDismissListener {
                Log.d(LOG_TAG,"Dismiss")
            }
            return dialog
        }
        return super.onCreateDialog(id)
    }
}
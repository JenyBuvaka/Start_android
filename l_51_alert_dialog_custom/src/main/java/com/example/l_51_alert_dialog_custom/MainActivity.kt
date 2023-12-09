package com.example.l_51_alert_dialog_custom

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.Color.parseColor
import android.hardware.lights.Light
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.green
import androidx.core.graphics.toColor
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private val Dialog = 1
    var btn by Delegates.notNull<Int>()
    lateinit var view:LinearLayout
     var tvCount: TextView? = null
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("HH:mm:ss")
     var textView: ArrayList<TextView> = ArrayList(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            btn = it.getId();
            showDialog(Dialog)
        }
        val btnRemove = findViewById<Button>(R.id.btnRemove)
        btnRemove.setOnClickListener {
            btn = it.getId();
            showDialog(Dialog)
        }
    }


    @SuppressLint("InflateParams")
    override fun onCreateDialog(id: Int): Dialog {
        val adb = AlertDialog.Builder(this)
        adb.setTitle("CustomDialog")
        view = (layoutInflater.inflate(R.layout.dialog,null)) as LinearLayout
        adb.setView(view)
       // tvCount = view.findViewById(R.id.tvCount) as TextView
        return adb.create()
    }

    @SuppressLint("SetTextI18n")
    override fun onPrepareDialog(id: Int, dialog: Dialog?) {
        super.onPrepareDialog(id, dialog)
        val color = Color.parseColor("#FFFFFFFF")
        if (id == Dialog){
            val tvTime = dialog?.window?.findViewById<TextView>(R.id.tvTime)
            tvTime?.text=sdf.format(Date(System.currentTimeMillis()))
            if (btn == R.id.btnAdd){
                val tv = TextView(this)
                view.addView(tv, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                tvTime?.text=sdf.format(Date(System.currentTimeMillis()))

                tv.text = "TextView ${textView.size +1} ${tvTime?.text}"
                tv.setTextColor(color)
                textView.add(tv)
            }else{
                if (textView.size>0){
                    val tv = textView.get(textView.size - 1)
                    view.removeView(tv)
                    textView.remove(tv)
                }
            }
            tvCount!!.text = "Кол-во TextView = ${textView.size}"

        }
    }
}
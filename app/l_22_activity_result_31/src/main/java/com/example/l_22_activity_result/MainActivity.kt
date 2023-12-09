package com.example.l_22_activity_result

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var tvText:TextView
    lateinit var btnColor:Button
    lateinit var btnAlign:Button

    val REQUEST_CODE_COLOR = 1
    val REQUEST_CODE_ALIGN = 2
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tvText)
        btnAlign = findViewById(R.id.btnAlign)
        btnColor = findViewById(R.id.btnColor)

        var intent = Intent()
        btnColor.setOnClickListener {
            intent = Intent(this,color::class.java)
            startActivityForResult(intent,REQUEST_CODE_COLOR)
        }
        btnAlign.setOnClickListener {
            intent = Intent(this,Align::class.java)
            startActivityForResult(intent,REQUEST_CODE_ALIGN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // запишем в лог значения requestCode и resultCode
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode)
        if (resultCode == RESULT_OK){
            when(requestCode){
                REQUEST_CODE_COLOR->{
                    val color = data!!.getIntExtra("color",Color.WHITE)
                    tvText.setTextColor(color)
                }
                REQUEST_CODE_ALIGN->{
                    val align = data!!.getIntExtra("alignment", Gravity.LEFT)
                    tvText.gravity = align
                }else->{Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show()}
            }

        }
    }
}
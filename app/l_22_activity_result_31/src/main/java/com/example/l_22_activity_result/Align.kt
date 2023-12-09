package com.example.l_22_activity_result

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class Align : AppCompatActivity(),OnClickListener {
    lateinit var btnLeft:Button
    lateinit var btnCenter:Button
    lateinit var btnRight:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_align)

        btnLeft = findViewById(R.id.btnLeft)
        btnCenter = findViewById(R.id.btnCenter)
        btnRight = findViewById(R.id.btnRight)

        btnRight.setOnClickListener (this)
        btnCenter.setOnClickListener (this)
        btnLeft.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        val intent = Intent()
        when(v?.id){
            R.id.btnRight->{
                intent.putExtra("alignment", Gravity.RIGHT)
            }
            R.id.btnLeft->{
                intent.putExtra("alignment", Gravity.LEFT)
            }
            R.id.btnCenter->{
                intent.putExtra("alignment", Gravity.CENTER)
            }
        }
        setResult(RESULT_OK,intent)
        finish()
    }
}
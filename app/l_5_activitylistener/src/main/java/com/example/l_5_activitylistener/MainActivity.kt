package com.example.l_5_activitylistener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView

class MainActivity : AppCompatActivity(),OnClickListener {
    lateinit var tvOut: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOut = findViewById(R.id.tvOut)

        val btnOk = findViewById<View>(R.id.btnOk)
        val btnCancel = findViewById<View>(R.id.btnCancel)

        btnOk.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btnOk->{
                    tvOut.text="qwe"
                }
                R.id.btnCancel->{
                    tvOut.text="sdfa"
                }

            }
        }
    }

}
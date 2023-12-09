package com.example.l_30_layoutinflater

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "myLOG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  itInflater = layoutInflater

        val linLayout = findViewById<LinearLayout>(R.id.linLayout)
        val view = itInflater.inflate(R.layout.text,linLayout,true)
        val lp = view.layoutParams


        //linLayout.addView(view)
        //Мы нашли linLayout с экрана и добавили в него созданный с помощью LayoutInflater элемент

        Log.d(LOG_TAG, "Class of view: " + view.javaClass.toString())
        Log.d(LOG_TAG, "LayoutParams of view is null: " + (lp == null))

        val relLayout = findViewById<RelativeLayout>(R.id.relLayout)
        val view2 = itInflater.inflate(R.layout.text,relLayout,true)
        val lp2 = view2.layoutParams

        Log.d(LOG_TAG, "Class of view2: " + view2.javaClass.toString())
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.javaClass.toString())
    }
}
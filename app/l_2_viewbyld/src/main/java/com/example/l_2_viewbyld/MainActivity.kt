package com.example.l_2_viewbyld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myText = findViewById<TextView>(R.id.MyText)
        myText.setText("NewText")
        val Button = findViewById<Button>(R.id.Button)
        Button.setText("NewText")
        val CHeckBox = findViewById<CheckBox>(R.id.Check)
        CHeckBox.isChecked
    }
}
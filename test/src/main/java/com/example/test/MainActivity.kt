package com.example.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),onClickList {
    lateinit var tv:TextView
//    private val blankFragment = BlankFragment().apply { listener(this@MainActivity) }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn).apply { setOnClickListener { tv.text = "HAHA" } }

        tv=findViewById(R.id.tv)
//        val fragmentTransaction = supportFragmentManager
//            .beginTransaction()
//        fragmentTransaction.apply {
//            add(R.id.fragmentContainerView,blankFragment)
//            commit()
//        }
    }

    override fun onClick() {

        tv.text="WORk"
    }
}
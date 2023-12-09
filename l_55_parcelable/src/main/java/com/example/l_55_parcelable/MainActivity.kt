package com.example.l_55_parcelable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val LOG_TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button1)
        btn.setOnClickListener {
            val myObj = MyObject("text",1)
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(MyObject::class.java.canonicalName,myObj)
            Log.d(LOG_TAG,"startActivity")
            startActivity(intent)
        }
    }
}
package com.example.l_56_saveinstance_state

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "myLog"
    private var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG,"onCreate")

        val btn = findViewById<Button>(R.id.button1)
        btn.setOnClickListener {
            Toast.makeText(this,"Count = ${++cnt}",Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG,"onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG,"onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG,"onRestart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cnt = savedInstanceState.getInt("count")
        Log.d(LOG_TAG,"onRestoeInstac1eState")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG,"onResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG,"onSaveInstanceState")
        outState.putInt("count",cnt)
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG,"onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG,"onStop")
    }
}
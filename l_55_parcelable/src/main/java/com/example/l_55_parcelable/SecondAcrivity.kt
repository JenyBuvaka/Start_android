package com.example.l_55_parcelable

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    private val LOG_TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)

        Log.d(LOG_TAG,"getParselableExtra")
        val myObj:MyObject = intent.getParcelableExtra(MyObject::class.java.canonicalName)!!
        Log.d(LOG_TAG,"myObj: "+ myObj.s+", " + myObj.i)
    }
}
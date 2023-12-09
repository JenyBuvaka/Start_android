package com.example.l_60_preferences_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import java.util.prefs.Preferences
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mi = menu?.add(0,1,0,"Preferences")
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.toString()){
//            "Preferences"->{
//                val fragment = FragmentActivity()
//                supportFragmentManager.beginTransaction().
//                replace(R.id.fragment,fragment).
//                addToBackStack(null).
//                commit()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

}
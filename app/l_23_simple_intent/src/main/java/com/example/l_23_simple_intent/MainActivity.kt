package com.example.l_23_simple_intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class MainActivity : AppCompatActivity(),OnClickListener {
    lateinit var btnWeb:Button
    lateinit var btnMap:Button
    lateinit var btnCall:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWeb = findViewById(R.id.btnWeb)
        btnCall = findViewById(R.id.btnCall)
        btnMap = findViewById(R.id.btnMap)

        btnMap.setOnClickListener(this)
        btnWeb.setOnClickListener(this)
        btnCall.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var intent = Intent()
        when(v?.id){
            R.id.btnWeb->{
                intent = Intent(Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com")))
                startActivity(intent)
                // ACTION_VIEW. Это константа в классе Intent – означает, что мы хотим просмотреть что-либо
            }
            R.id.btnMap->{
                var intent = Intent()
                intent.setAction(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("geo:55.754283,37.62002"));
                startActivity(intent)
            }
            R.id.btnCall->{
               var intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:12345"))
                startActivity(intent)
            }
        }
    }
}
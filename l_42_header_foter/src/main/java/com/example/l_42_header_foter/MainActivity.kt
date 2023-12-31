package com.example.l_42_header_foter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.HeaderViewListAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "myLogs"

    val data = arrayListOf("one", "two", "three", "four", "five")
    lateinit var lvMain:ListView
    lateinit var adapter:ArrayAdapter<String>

    lateinit var header1:View
    lateinit var header2:View
    lateinit var footer1:View
    lateinit var footer2:View

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvMain = findViewById(R.id.lvMain)
        adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)

        //создаем header and Footer
        header1 = createHeader("header 1")
        header2 = createHeader("header 2")
        footer1 = createFooter("footer 1")
        footer2 = createFooter("footer 2")

        val btn = findViewById<Button>(R.id.button1)
        btn.setOnClickListener {
            val hvlAdapter = lvMain.adapter as HeaderViewListAdapter
        var obj: Any? =hvlAdapter.getItem(1)
            Log.d(LOG_TAG, "hvlAdapter.getItem(1) = $obj");
            obj = hvlAdapter.getItem(4);
            Log.d(LOG_TAG, "hvlAdapter.getItem(4) = $obj");

            val alAdapter = hvlAdapter.wrappedAdapter as ArrayAdapter<*>
            obj = alAdapter.getItem(1)
            Log.d(LOG_TAG, "alAdapter.getItem(1) = $obj");
            obj = alAdapter.getItem(4);
            Log.d(LOG_TAG, "alAdapter.getItem(4) = $obj");
       }
        fillList()

    }
    //Формирование списка
    fun fillList(){
        lvMain.addHeaderView(header1)
        lvMain.addHeaderView(header2,"some text for header 2", false)
        lvMain.addFooterView(footer1)
        lvMain.addFooterView(footer2,"some text for footer 2", false)
        lvMain.adapter = adapter
    }
    //создаем Header
    @SuppressLint("InflateParams")
    fun createHeader(text:String):View{
        val v:View = layoutInflater.inflate(R.layout.header,null)
        v.findViewById<TextView>(R.id.tvText).text = text
        return v
    }
    //СОздаём Footer
    fun  createFooter(text: String):View{
        val v = layoutInflater.inflate(R.layout.footer,null)
        v.findViewById<TextView>(R.id.tvText).text = text
        return v
    }
}
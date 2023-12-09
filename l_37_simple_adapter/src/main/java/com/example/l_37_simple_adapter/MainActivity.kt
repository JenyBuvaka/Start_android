package com.example.l_37_simple_adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import java.util.ArrayList
import java.util.Objects

class MainActivity : AppCompatActivity() {
    //Аттрибуты для MAP
    private val ATTRIBUTE_NAME_TEXT = "text"
    private val ATTRIBUTE_NAME_CHECKED = "checked"
    private val  ATTRIBUTE_NAME__IMAGE = "image"

    lateinit var lvSimple:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //массив данных
        val texts = arrayOf("sometext 1", "sometext 2", "sometext 3",
            "sometext 4", "sometext 5")
        val checked = arrayOf(true, false, false, true, false )
        val image = R.drawable.ic_launcher_foreground

        // упаковываем данные в понятную для адаптера структуру
        val data = ArrayList<Map<String,Any>>(texts.size)
        var m:MutableMap<String,Any>
        for (i in texts.indices){
            m = HashMap()
            m[ATTRIBUTE_NAME_TEXT]= texts[i]
            m[ATTRIBUTE_NAME_CHECKED] = checked[i]
            m[ATTRIBUTE_NAME__IMAGE] = image
            data.add(m)

        }
        // массив имен атрибутов, из которых будут читаться данные
        val from = arrayOf(ATTRIBUTE_NAME_TEXT,ATTRIBUTE_NAME_CHECKED,ATTRIBUTE_NAME__IMAGE,ATTRIBUTE_NAME_TEXT)//Почему мне тут не подходит arrayListOf
        // массив ID View-компонентов, в которые будут вставлять данные
        val  to = intArrayOf(R.id.tvText, R.id.cbChecked, R.id.ivImg,R.id.cbChecked)//И тут та же история , иначе выдает ошибку в адаптере

        //Создаем Адаптер
        val apdapter = SimpleAdapter(this,data,R.layout.item,from,to)
        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = apdapter
    }

}
//
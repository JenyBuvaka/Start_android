package com.example.l_33_simple_list_choice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    lateinit var lvMain:ListView
    lateinit var names:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvMain=findViewById(R.id.lvMain)
        // устанавливаем режим выбора пунктов списка
        lvMain.choiceMode=ListView.CHOICE_MODE_MULTIPLE
    // Создаем адаптер, используя массив из файла ресурсов
        val adapter=ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_list_item_single_choice)
        lvMain.adapter=adapter

        val btnChecked=findViewById<Button>(R.id.btnChecked)
        btnChecked.setOnClickListener {
            //пишем в лог выделенный элемент
            Log.d(LOG_TAG, "checked: ")
            // Получаем SparseBooleanArray с выделенными элементами из списка (lvMain)
            val sbArray:SparseBooleanArray = lvMain.checkedItemPositions
            // Итерируем по SparseBooleanArray
            for (i in 0 until sbArray.size()){
                // Получаем ключ (индекс) элемента в SparseBooleanArray
                val key =  sbArray.keyAt(i)
                // Проверяем, выделен ли элемент по ключу
                if (sbArray.get(key)){
                    // Если элемент выделен, записываем его имя в лог
                    Log.d(LOG_TAG,names[key])}
            }
        }
        names = resources.getStringArray(R.array.names)

    }

}
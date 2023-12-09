package com.example.l_39_simple_adapter_custom2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    private val CM_DELETE_ID = 1

    // имена атрибутов для Map
    private val ATTRIBUTE_NAME_TEXT = "text"
    private val ATTRIBUTE_NAME_IMAGE = "image"

    lateinit var lvSimple:ListView
    lateinit var sAdapter:SimpleAdapter
    private val data=ArrayList<Map<String,Any>>()
    private val m=HashMap<String,Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // упаковываем данные в понятную для адаптера структуру
        for (i in 1 until 5 ){
            m.clear()
            m[ATTRIBUTE_NAME_TEXT]= "sometext "+i
            m[ATTRIBUTE_NAME_IMAGE]= R.drawable.ic_launcher_foreground
            data.add(m)
        }

        // массив имен атрибутов, из которых будут читаться данные
        val from = arrayOf(ATTRIBUTE_NAME_TEXT,ATTRIBUTE_NAME_IMAGE)
        val to = intArrayOf(R.id.tvText,R.id.ivImg)

        //Создаём адаптер
        sAdapter = SimpleAdapter(this,data,R.layout.item,from, to)
        // определяем список и присваиваем ему адаптер
        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter
        registerForContextMenu(lvSimple)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            //Создаем новый Map
            m.clear()
            m[ATTRIBUTE_NAME_TEXT]= "sometext"+(data.size+1)
            m[ATTRIBUTE_NAME_IMAGE]= R.drawable.ic_launcher_foreground
            // добавляем его в коллекцию
            data.add(m)
            // уведомляем, что данные изменились
            sAdapter.notifyDataSetChanged()

        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (menu != null) {
            menu.add(0,CM_DELETE_ID,0,"Delete text")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId==CM_DELETE_ID){
            //Получаем инфу о пункте списка
            val acmi = item.menuInfo as AdapterContextMenuInfo
            // удаляем Map из коллекции, используя позицию пункта в списке
            data.removeAt(acmi.position)
            sAdapter.notifyDataSetChanged()
            return true
        }
        return super.onContextItemSelected(item)

    }
}
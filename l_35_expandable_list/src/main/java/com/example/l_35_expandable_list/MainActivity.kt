package com.example.l_35_expandable_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter

class MainActivity : AppCompatActivity() {
    // названия компаний (групп)
    val groups = arrayListOf("HTC","Samsung","LG")
    // названия телефонов (элементов)
    val phonesHTC = arrayListOf("Sensation", "Desire", "Wildfire", "Hero")
    val phonesSamsung = arrayListOf("Galaxy S II", "Galaxy Nexus", "Wave")
    val phonesLG = arrayListOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

    // коллекция для групп
    lateinit var groupData:ArrayList<Map<String,String>>
    // коллекция для элементов одной группы
    lateinit var childDataItem:ArrayList<Map<String,String>>

    // общая коллекция для коллекций элементоv
    lateinit var childData:ArrayList<ArrayList<Map<String,String>>>
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    lateinit var m:MutableMap<String,String>

    lateinit var elvMain:ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        childData= ArrayList()
        groupData= ArrayList()
        childDataItem=ArrayList()
        // заполняем коллекцию групп из массива с названиями групп
        for(group in groups){
            //Заполняем список атрибутов для каждой группы
            m=HashMap()
            m["groupName"]=group//Имя компании
            groupData.add(m)
        }
        //Список атрибутов группы для чтения
        val groupFrom = arrayOf("groupName")
        // список ID view-элементов, в которые будет помещены атрибуты групп
        val groupTo = intArrayOf(android.R.id.text1)

        // создаем коллекцию для коллекций элементов
        childData.clear()

        // создаем коллекцию элементов для первой группы
        childDataItem.clear()
        // заполняем список атрибутов для каждого элемента
        for (phone in phonesHTC){
            m=HashMap()
            m["phoneName"]=phone//Название телефона
            childDataItem.add(m)
        }
        // добавляем в коллекцию коллекций
        childData.add(ArrayList(childDataItem))


        // создаем коллекцию элементов для 2 группы
        childDataItem.clear()
        for(phone in phonesSamsung){
            m=HashMap()
            m["phoneName"]=phone
            childDataItem.add(m)
        }
        childData.add(ArrayList(childDataItem))

        //Создаем коллекцию элементов для 3 группы
        childDataItem.clear()
        for (phone in phonesLG) {
            m = HashMap()
            m["phoneName"] = phone
            childDataItem.add(m)
        }
        childData.add(ArrayList(childDataItem))
        //Список атрибутов элементов для чтения
        val childFrom = arrayOf("phoneName")
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        val childTo= intArrayOf(android.R.id.text1)
        val adapter = SimpleExpandableListAdapter(this,
            groupData,
            android.R.layout.simple_expandable_list_item_1,
            groupFrom,
            groupTo,
            childData,
            android.R.layout.simple_list_item_1,
            childFrom,
            childTo)
        elvMain=findViewById(R.id.elvMain)
        elvMain.setAdapter(adapter)
    }
}
package com.example.l_36_expandable_list_events

import android.content.Context
import android.widget.SimpleExpandableListAdapter

class AdapterHelper(private val ctx:Context) {
    private val ATTR_GROUP_NAME="groupName"
    private val ATTR_PHONE_NAME="phoneName"

    // названия компаний (групп)
   private val groups = arrayListOf("HTC","Samsung","LG")
    // названия телефонов (элементов)
    private val phonesHTC = arrayListOf("Sensation", "Desire", "Wildfire", "Hero")
    private val phonesSamsung = arrayListOf("Galaxy S II", "Galaxy Nexus", "Wave")
    private val phonesLG = arrayListOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

    // коллекция для групп
    private var groupData:ArrayList<Map<String,String>> = ArrayList()
    // коллекция для элементов одной группы
    private var childDataItem:ArrayList<Map<String,String>> = ArrayList()

    // общая коллекция для коллекций элементоv
    private var childData:ArrayList<ArrayList<Map<String,String>>> = ArrayList()
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    private var m:MutableMap<String,String> = HashMap()

    private lateinit var adapter: SimpleExpandableListAdapter

    fun getAdapter(): SimpleExpandableListAdapter {
        // заполняем коллекцию групп из массива с названиями групп
        groupData = ArrayList()
        for (group in groups) {
            // заполняем список атрибутов для каждой группы
            m = HashMap()
            m[ATTR_GROUP_NAME] = group // имя компании
            groupData.add(m)
        }

        // список атрибутов групп для чтения
        val groupFrom = arrayOf(ATTR_GROUP_NAME)
        // список ID view-элементов, в которые будет помещены атрибуты групп
        val groupTo = intArrayOf(android.R.id.text1)

        // создаем коллекцию для коллекций элементов
        childData = ArrayList()

        // создаем коллекцию элементов для первой группы
        childDataItem = ArrayList()
        // заполняем список атрибутов для каждого элемента
        for (phone in phonesHTC) {
            m = HashMap()
            m[ATTR_PHONE_NAME] = phone // название телефона
            childDataItem.add(m)
        }
        // добавляем в коллекцию коллекций
        childData.add(ArrayList(childDataItem))

        // создаем коллекцию элементов для второй группы
        childDataItem = ArrayList()
        for (phone in phonesSamsung) {
            m = HashMap()
            m[ATTR_PHONE_NAME] = phone
            childDataItem.add(m)
        }
        childData.add(ArrayList(childDataItem))

        // создаем коллекцию элементов для третьей группы
        childDataItem = ArrayList()
        for (phone in phonesLG) {
            m = HashMap()
            m[ATTR_PHONE_NAME] = phone
            childDataItem.add(m)
        }
        childData.add(ArrayList(childDataItem))

        // список атрибутов элементов для чтения
        val childFrom = arrayOf(ATTR_PHONE_NAME)
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        val childTo = intArrayOf(android.R.id.text1)

        adapter = SimpleExpandableListAdapter(
            ctx,
            groupData,
            android.R.layout.simple_expandable_list_item_1,
            groupFrom,
            groupTo,
            childData,
            android.R.layout.simple_list_item_1,
            childFrom,
            childTo
        )

        return adapter
    }
    fun getGroupText(groupPos:Int):String{
        return (adapter.getGroup(groupPos) as Map<*, *>)[ATTR_GROUP_NAME].toString()
    }
    fun getChildText(groupPos:Int,childPos:Int):String{
        return (adapter.getChild(groupPos , childPos) as Map<*, *>)[ATTR_PHONE_NAME].toString()
    }
    fun  getGroupChildText(groupPos:Int,childPos: Int):String{
        return getGroupText(groupPos) + " " + getChildText(groupPos,childPos)
    }
}
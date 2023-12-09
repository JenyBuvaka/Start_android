package com.example.l_32_simple_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private val names = arrayListOf("Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
        "Костя", "Игорь", "Анна", "Денис", "Андрей")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //находим список
        val lvMain = findViewById<ListView>(R.id.lvMain)
        //создаем адаптер
        val adapter = ArrayAdapter<String>(this,R.layout.my_list_item,names)
        //ПРИСВАИВАЕМ АДАПТЕР СПИСКУ
        lvMain.adapter = adapter
        //В качестве данных используем массив имен. В onCreate мы находим список, создаем адаптер и присваиваем адаптер списку. Давайте разберемся, как создали адаптер.
        //
        //Мы использовали этот конструктор: public ArrayAdapter (Context context, int textViewResourceId, T[] objects)
        //
        //и передали ему следующие параметры:
        //
        //this – контекст
        //android.R.layout.simple_list_item_1 – это системный layout-файл, который представляет собой TextView
        //names – массив данных, которые мы хотим вывести в список
    }
}
//Немного про Context
//На одном из прошлых уроков я говорил, что Context (контекст) используется для доступа к базовым функциям приложения.
// В этом уроке у нас получилось хорошее подтверждение этим словам.
//
//ArrаyAdapter использует LayoutInflater, чтобы конвертнуть layout-ресурс в View.
// Но получение объекта LayoutInflater – это одна из базовых функций и она недоступна для класса ArrаyAdapter. Поэтому мы в ArrаyAdapter
// в качестве контекста передаем ссылку на Activity (Activity имеет доступ к базовым функциям через восходящую иерархию классов).
// А класс ArrayAdapter внутри себя использует переданный ему контекст, чтобы вызвать LayoutInflater. Без контекста он не смог бы это сделать.
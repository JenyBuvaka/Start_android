package com.example.l_20_intent_extras

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
   lateinit var etFName:EditText
   lateinit var etLName:EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFName = findViewById(R.id.etFName)
        etLName = findViewById(R.id.etLName)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        //Используется метод putExtra. Он имеет множество вариаций и аналогичен методу put для Map, т.е. добавляет к объекту пару.
        // Первый параметр – это ключ(имя), второй - значение.
        //
        //Мы поместили в Intent два объекта с именами: fname и lname. fname содержит значение поля etFName, lname – значение поля etLName.
        // Остается только отправить укомплектованный Intent с помощью метода startActivity.
        btnSubmit.setOnClickListener {
            val intent = Intent(this,ViewActivity::class.java)
            intent.putExtra("fname",etFName.text.toString())
            intent.putExtra("lname",etLName.text.toString())
            startActivity(intent)
        }
    }
}
package com.example.l_7_logndmess

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VIEW_LOG_TAG
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(),OnClickListener {
    lateinit var tvOut:TextView
    lateinit var btnOk:Button
    lateinit var btnCancel:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// найдем View-элементы
        Log.d(TAG,"Найдём элементы Вью")
        tvOut=findViewById(R.id.tvOut)
        btnOk=findViewById(R.id.btnOk)
        btnCancel=findViewById(R.id.btnCancel)
// присваиваем обработчик кнопкам
        Log.d(TAG,"Присваиваем обработчика кнопкам")
        btnOk.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d(TAG,"По айди определяем кнопку вызвавшею функцию")
        when(v!!.id){
            R.id.btnOk->{
                Log.d(TAG, "кнопка ОК")
                tvOut.text="Нажата кнопка ОК"
                Toast.makeText(this, "Нажата кнопка ОК", Toast.LENGTH_LONG).show()
//                Разберем синтаксис вызова. Статический метод makeText создает View-элемент Toast. Параметры метода:
//
//                - context – пока не будем вдаваться в подробности, что это такое и используем текущую Activity, т.е. this.
//                - text – текст, который надо показать
//                - duration – продолжительность показа (Toast.LENGTH_LONG - длинная, Toast.LENGTH_SHORT - короткая)
//
//                Toast создан и чтобы он отобразился на экране, вызывается метод show(). Сохраняем, запускаем, проверяем.
            }
            R.id.btnCancel->{
                Log.d(TAG, "кнопка Cancel")
                tvOut.text="Нажата кнопка Кансел"
                Toast.makeText(this, "Нажата кнопка Cancel", Toast.LENGTH_LONG).show()
            }
        }
    }
}
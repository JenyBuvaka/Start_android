package com.example.l_58_preferences_simple2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvInfo:TextView
    private lateinit var sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInfo = findViewById(R.id.tvInfo)
        sp = PreferenceManager.getDefaultSharedPreferences(this)

    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        val listValues = sp.getString("list","не выбрано")
        tvInfo.text = "Значение списка - $listValues"
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mi = menu?.add(0,1,0,"Preferences")
        mi?.intent = Intent(this,PrefActivity::class.java)
        return super.onCreateOptionsMenu(menu)
    }
}
//В onCreate получаем доступ к настройкам.
//
//В onResume выводим в tvInfo значение из настроек, которое записал туда список. Если значений нет, то пишем текст «не выбрано»
//
//В onCreateOptionsMenu создаем пункт меню и вешаем на него Intent, который запустит нам экран настроек.
//
//Остальные параметры читать не буду, это мы на прошлом уроке рассмотрели, там все просто.
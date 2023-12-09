package com.example.l_57_prefences_simple

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    lateinit var tvInfo:TextView
    lateinit var sp:SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById(R.id.tvInfo)

// получаем SharedPreferences, которое работает с файлом настроек
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        // полная очистка настроек
        // sp.edit().clear().commit();
    }

    override fun onResume() {
        val notif = sp.getBoolean("notif",false)
        val address = sp.getString("address","")
        val text = ("Notifications are "
                + if (notif) "enabled, address = $address" else "disabled")
        tvInfo.text = text
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mi = menu?.add(0,1,0,"Preferences")
        mi?.setIntent(Intent(this,PrefActivity::class.java))
        return super.onCreateOptionsMenu(menu)

    }
}
//В onCreate мы находим TextView и получаем объект для работы с настройками - SharedPreferences. Он нам знаком, мы с ним работали ранее в уроке про Preferences. Далее идет закоментированный код полной очистки настроек приложения. Мы его не используем, я на всякий случай указал, может кому понадобится.
//
//В onResume мы читаем из SharedPreferences настройки и выводим их в TextView. При чтении используем те самые ключи, которые прописывали в xml-файле в атрибутах key.
//
//В onCreateOptionsMenu просто настраиваем меню для вызова окна настроек. Мы создаем пункт меню и вешаем на него Intent - в итоге при нажатии вызовется Activity.
//
//
//
//На всякий случай уточню, почему для вывода инфы на экран здесь использую onResume, а не onCreate. Потому что, когда мы будем возвращаться с экрана настроек в главное окно, то onCreate главного окна не сработает (т.к. окно уже создано и висит в памяти) и изменения мы не увидим. А onResume точно сработает.
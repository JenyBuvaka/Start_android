package com.example.l_57_prefences_simple

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceActivity

//Создаем Activity для настроек. PrefActivity.java:


 class PrefActivity(): PreferenceActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         addPreferencesFromResource(R.xml.pref)
     }
     //Вместо setContentView используется метод addPreferencesFromResource, который берет файл pref.xml и по нему создает экран настроек.

}
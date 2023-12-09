package com.example.l_59_prefences_enable

import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat

class PrefActivity:PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref,rootKey)
        val chb3 = findPreference<CheckBoxPreference>("chb3")
        val  categ2 = findPreference<PreferenceCategory>("categ2")
        categ2?.isEnabled = chb3!!.isChecked

        chb3.setOnPreferenceChangeListener { preference, newValue ->
            categ2?.isEnabled = newValue as Boolean
            true
        }
    }
}
//Итак, что происходит в этом коде:
//
//Мы создаем класс PrefFragment, который наследуется от PreferenceFragmentCompat, что позволяет использовать фрагменты настройки вместо устаревшей PreferenceActivity.
//
//Мы объявляем chb3 и categ2 как переменные класса и инициализируем их в методе onCreatePreferences, где также загружаем настройки из XML-ресурса с помощью setPreferencesFromResource.
//
//Мы используем оператор as для приведения типов, чтобы преобразовать найденные настройки в CheckBoxPreference и PreferenceCategory.
//
//Мы устанавливаем начальное значение isEnabled для categ2 в зависимости от состояния флажка chb3.
//
//Мы добавляем слушатель событий OnPreferenceClickListener для chb3, чтобы обновлять состояние isEnabled для categ2 при изменении состояния флажка.
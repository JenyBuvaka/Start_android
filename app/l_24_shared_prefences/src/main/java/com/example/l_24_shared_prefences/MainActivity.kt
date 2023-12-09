package com.example.l_24_shared_prefences

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY

class MainActivity : AppCompatActivity() {
    lateinit var etTexst:EditText
    lateinit var btnSave: Button
    lateinit var btnLoad: Button
    val SAVED_TEXT = "saved text"
    //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTexst = findViewById(R.id.etText)
        btnLoad = findViewById(R.id.btnLoad)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener{
        saveText()
    }
        btnLoad.setOnClickListener {
            loadText()
        }
        loadText()
    }

    private fun loadText() {
        val sPref = getPreferences(MODE_PRIVATE)
        var savedText = sPref.getString(SAVED_TEXT,"")
        etTexst.setText(savedText)
        Toast.makeText(this,"Text loaded",Toast.LENGTH_SHORT).show()
        //loadText – загрузка данных. Так же, как и saveText, с помощью метода getPreferences получаем объект sPref класса SharedPreferences.
    // MODE_PRIVATE снова указывается, хотя и используется только при записи данных. Здесь Editor мы не используем, т.к. нас
    // интересует только чтение данных. Читаем с помощью метода getString – в параметрах указываем константу - это имя, и значение по умолчанию (пустая строка)
    }

    private fun saveText() {
        val sPref = getPreferences(MODE_PRIVATE)
            //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        //loadText – загрузка данных. Так же, как и saveText, с помощью метода getPreferences получаем объект sPref класса SharedPreferences.
        // MODE_PRIVATE снова указывается, хотя и используется только при записи данных. Здесь Editor мы не используем, т.к. нас интересует только чтение данных.
        // Читаем с помощью метода getString – в параметрах указываем константу - это имя, и значение по умолчанию (пустая строка)
        val ed = sPref.edit()
        //Далее, чтобы редактировать данные, необходим объект Editor – получаем его из sPref
        //В метод putString указываем наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
        ed.putString(SAVED_TEXT, etTexst.text.toString())
        ed.commit()
        Toast.makeText(this,"Text saved",Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        saveText()
    }
}
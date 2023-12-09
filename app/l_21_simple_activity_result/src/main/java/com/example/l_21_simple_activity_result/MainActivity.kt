package com.example.l_21_simple_activity_result

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var tvName:TextView
    lateinit var btnName:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvName = findViewById(R.id.tvName)
        btnName = findViewById(R.id.btnName)

        btnName.setOnClickListener {
            val intent = Intent(this,NameActivity::class.java)
            startActivityForResult(intent, 1)
            //В startActivityForResult в качестве параметров мы передаем Intent и requestCode. requestCode – необходим для идентификации.
            // В этом уроке мы его укажем, но не будем использовать по назначению
        }
    }
//onActivityResult мы видим следующие параметры:
//requestCode – тот же идентификатор, что и в startActivityForResult. По нему определяем, с какого Activity пришел результат.
//resultCode – код возврата. Определяет успешно прошел вызов или нет.
//data – Intent, в котором возвращаются данные
//
//requestCode и resultCode мы пока использовать не будем, подробнее рассмотрим их на следующем уроке.
// А из data мы будем получать объект по имени name и выводить значение в TextView.
//
//Если мы извлекаем из Intent объект с именем name, значит надо, чтобы кто-то его туда положил. Этим займется NameActivity.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null){return }
        val name = data.getStringExtra("name")
        tvName.text = "Your name is:$name"
    }
}
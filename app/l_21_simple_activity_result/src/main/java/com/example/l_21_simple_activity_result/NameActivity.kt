package com.example.l_21_simple_activity_result

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NameActivity : AppCompatActivity() {
    lateinit var etName:EditText
    lateinit var btnOk:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        etName = findViewById(R.id.etName)
        btnOk = findViewById(R.id.btnOK)

        btnOk.setOnClickListener {
           val intent = Intent()
            intent.putExtra("name",etName.text.toString())
            setResult(RESULT_OK,intent)
            // в setResult мы передаем константу RESULT_OK, означающую успешное завершение вызова
            finish()
        }
    }
}
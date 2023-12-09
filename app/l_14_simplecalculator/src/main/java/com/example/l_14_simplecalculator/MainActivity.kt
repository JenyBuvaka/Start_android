package com.example.l_14_simplecalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(),OnClickListener {
    val MENU_RESET_ID = 1
    val MENU_QUIT_ID = 2

    lateinit var etNum1: EditText
    lateinit var etNum2: EditText

    lateinit var btnAdd:Button
    lateinit var btnSub:Button
    lateinit var btnMult:Button
    lateinit var btnDiv:Button

    lateinit var tvResult:TextView

    var oper = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //находим элементы
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);

        tvResult = findViewById(R.id.tvResult);
        //прописываем обработчик
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        var num1 = 0F
        var num2 = 0F
        var result = 0F
        //проверяем поля на пустоту
        if (TextUtils.isEmpty(etNum1.text.toString()) || TextUtils.isEmpty(etNum2.text.toString())) {
            return
        }
        //читаем EditText и заполняем переменные числа
        num1=etNum1.text.toString().toFloat()
        num2=etNum2.text.toString().toFloat()

        when(v?.id){
            R.id.btnAdd->{
                oper = "+"
                result=num1+num2
            }
            R.id.btnSub->{
                oper = "-"
                result = num1-num2
            }
            R.id.btnMult->{
                oper = "*"
                result = num1*num2
            }
            R.id.btnDiv->{
                oper = "/"
                result = num1/num2
            }
        }
        tvResult.text = "$num1 $oper $num2 = ${result}"
    }
    // создание меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,MENU_RESET_ID,0,"RESET")
        menu?.add(0,MENU_QUIT_ID,0,"QUIT")
        return super.onCreateOptionsMenu(menu)
    }
// обработка нажатий на пункты меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
        MENU_RESET_ID->{
            //очищаем поля
            etNum1.setText("")
            etNum2.setText("")
            tvResult.setText("")
        }
        MENU_QUIT_ID->{
            //выход из приложения
            finish()
        }
    }
        return super.onOptionsItemSelected(item)
    }
}
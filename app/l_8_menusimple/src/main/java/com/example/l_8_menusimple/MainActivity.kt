package com.example.l_8_menusimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add("menu1")
        menu.add("menu2")
        menu.add("menu3")
        menu.add("menu4")
        menu.add("menu5")
        return super.onCreateOptionsMenu(menu)
//        Пункты меню добавляются методом add. На вход методу подается текст пункта меню. Добавим 4 пункта.
//        Метод onCreateOptionsMenu должен вернуть результат типа boolean. True – меню показывать, False – не показывать.
//        Т.е. можно было бы накодить проверку какого-либо условия, и по итогам этой проверки не показывать меню передавая False.
//        Пока нам это не нужно, поэтому поручаем этот выбор методу суперкласса, по умолчанию он возвращает True.

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this,item.title,Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
//        Обработчиком является Activity, а метод зовется onOptionsItemSelected.
    //        На вход ему передается пункт меню, который был нажат – MenuItem.
//        Определить, какое именно меню было нажато можно по методу title.
    //        Давайте выводить всплывающее сообщение с текстом нажатого пункта меню.
//        На выходе метода надо возвращать boolean. И мы снова предоставляем это суперклассу.
    }
}
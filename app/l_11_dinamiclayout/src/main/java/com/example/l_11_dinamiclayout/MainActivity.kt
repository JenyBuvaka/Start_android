package com.example.l_11_dinamiclayout

import android.app.ActionBar.LayoutParams
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// создание LinearLayout
        val linLayout = LinearLayout(this)
        // создание LinearLayout
        linLayout.orientation=LinearLayout.VERTICAL
        // создаем LayoutParams
        val linLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
// устанавливаем linLayout как корневой элемент экрана
        setContentView(linLayout,linLayoutParams)
        //Давайте разберем код. Мы создаем LinearLayout и ставим вертикальную ориентацию.
    // Далее создаем LayoutParams. Конструктор на вход принимает два параметра: width и height.
    // Мы оба ставим MATCH_PARENT. Далее вызывается метод setContentView.
    // На вход ему подается LinearLayout и LayoutParams. Это означает,
    // что корневым элементом Activity будет LinearLayout с layout-свойствами из LayoutParams.
        val ipView = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
//        Объект lpView имеет базовый тип android.view.ViewGroup.LayoutParams.
//        А значит позволит настроить только ширину и высоту. Но для View в LinearLayout доступны,
//        например, отступ слева или выравнивание по правому краю. И если мы хотим их задействовать,
//        значит надо использовать LinearLayout.LayoutParams:

        val tv = TextView(this)
        tv.text = "TextView"
        tv.layoutParams = ipView
        linLayout.addView(tv)

        val btn = Button(this)
        btn.setText("Button")
        btn.setTextSize(50F)
        btn.setTextColor(Color.GREEN)
        btn.setBackgroundColor(Color.BLUE)
        linLayout.addView(btn,ipView)
//        Мы снова создаем объект LayoutParams с атрибутами width = wrap_content и height = wrap_content. Теперь если мы присвоим этот объект какому-либо View, то это View будет иметь ширину и высоту по содержимому.
//
//        Далее мы создаем TextView, настраиваем его текст, присваиваем ему выше созданный LayoutParams и добавляем в LinearLayout с помощью метода addView (View child).
//
//        С Button аналогично – создаем, правим текст, а затем используем другую реализацию метода addView (View child, ViewGroup.LayoutParams params), которая одновременно добавляет Button в LinearLayout и присваивает для Button указанный LayoutParams. Результат будет тот же, что и с TextView, но вместо двух строк кода получилась одна.
//
//        Обратите внимание, что для двух объектов View я использовал один объект LayoutParams - lpView. Оба View-объекта считают параметры из LayoutParams и используют их.
        val leftMarginParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        leftMarginParams.leftMargin = 50

        val btn1 = Button(this)
        btn1.text = "Button1"
        linLayout.addView(btn1,leftMarginParams)
        //Смотрим код. Мы создаем объект типа LinearLayout.LayoutParams с помощью такого же конструктора,
    // как и для обычного LayoutParams, указывая width и height. Затем мы указываем отступ слева = 50.
    // Отступ здесь указывается в пикселах. Далее схема та же: создаем объект, настраиваем текст и
    // добавляем его в LinearLayout c присвоением LayoutParams.
        //Аналогично добавим компонент с выравниванием:
        val rightGravityParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        rightGravityParams.gravity=Gravity.RIGHT

        val btn2 = Button(this)
        btn2.text= "Button2"
        linLayout.addView(btn2,rightGravityParams)
    }
//    Известные нам примеры ViewGroup – это LinearLayout, TableLayout, RelativeLayout и т.д.
//    Каждая из этих ViewGroup имеет вложенный класс LayoutParams. Базовым для этих LayoutParams
//    является ViewGroup.LayoutParams.
//
//    ViewGroup.LayoutParams имеет всего два атрибута: height и width.
//    Его подкласс ViewGroup.MarginLayoutParams наследует два этих атрибута и
//    имеет свои четыре: bottomMargin, leftMargin, rightMargin, topMargin.
//    Класс LinearLayout.LayoutParams в свою очередь является подклассом ViewGroup.MarginLayoutParams,
//    наследует от него уже 6 аттрибутов и добавляет свои два: gravity и weight.
//
//    Т.е. объект LinearLayout имеет вложенный класс LinearLayout.LayoutParams с layout-аттрибутами.
//    И эти аттрибуты распространяются на все дочерние View и ViewGroup

}
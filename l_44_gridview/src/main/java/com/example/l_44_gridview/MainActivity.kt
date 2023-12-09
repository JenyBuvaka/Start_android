package com.example.l_44_gridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    private val data = arrayListOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")
    private lateinit var gvMain:GridView
    private lateinit var adapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter= ArrayAdapter(this,R.layout.item,R.id.tvText,data)
        gvMain = findViewById(R.id.gvMain)
        gvMain.adapter = adapter
        adjustGridView()
    }

    private fun adjustGridView() {
        //numColumns – кол-во столбцов в сетке. Если его не задавать, то столбец будет по умолчанию один.
        //Это свойство также может иметь значение AUTO_FIT. В этом случае проверяется значение поля атрибута columnWidth (ширина столбца).
        //
        //- если ширина столбца явно указана, то кол-во столбцов рассчитывается исходя из ширины, доступной GridView, и ширины столбцов.
        //
        //- иначе, кол-во столбцов считается равным 2
        gvMain.numColumns = GridView.AUTO_FIT
        //Теперь укажем явно ширину столбцов, пусть будет 50
        gvMain.columnWidth = 50
        //horizontalSpacing, verticalSpacing
        //Это горизонтальный и вертикальный отступы между ячейками
        gvMain.verticalSpacing = 100
        gvMain.horizontalSpacing = 200
        //stretchMode
        //Этот параметр определяет, как будет использовано свободное пространство, если оно есть. Используется в случае, когда вы указываете ширину столбца и кол-во ставите в режим AUTO_FI
        gvMain.stretchMode

    }
}
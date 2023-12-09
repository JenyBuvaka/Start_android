package com.example.l_38_simple_adapter_custom1

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Color.BLUE
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val ATTRIBUTE_NAME_TEXT = "text"
    private val ATTRIBUTE_NAME_VALUE = "checked"
    private val  ATTRIBUTE_NAME_IMAGE = "image"

    // картинки для отображения динамики
    private val  positive = android.R.drawable.arrow_up_float
    private val negative = android.R.drawable.arrow_down_float

    private lateinit var lvSimple:ListView
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val values = arrayListOf( 8, 4, -3, 2, -5, 0, 3, -6, 1, -1)
        // упаковываем данные в понятную для адаптера структуру
        val data = ArrayList<Map<String,Any>>(values.size)
        var m:Map<String,Any>
        var img = 0
        for (i in values.indices){
            m =HashMap()
            m[ATTRIBUTE_NAME_TEXT]="Day " + (i+1)
            m[ATTRIBUTE_NAME_VALUE]= values[i]
            img= if (values[i] == 0){ 0
            }else {
                if (values[i] > 0) positive else negative
            }
        m[ATTRIBUTE_NAME_IMAGE]=img
            data.add(m)
        }
        // массив имен атрибутов, из которых будут читаться данные
        val from = arrayOf(ATTRIBUTE_NAME_TEXT,ATTRIBUTE_NAME_VALUE,ATTRIBUTE_NAME_IMAGE)
        // массив ID View-компонентов, в которые будут вставлять данные
        val to = intArrayOf(R.id.tvText, R.id.tvValue, R.id.ivImg )
        //Создаем адаптер
        val sAdapter = MySimpleAdapter(this,data,R.layout.item,from,to)
        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter

        }

    inner class MySimpleAdapter(context: Context, data:List<Map<String,*>>?, resources:Int, from:Array<out String>, to:IntArray): SimpleAdapter(context,data,resources,from,to) {
        override fun setViewText(v: TextView?, text: String?) {
            super.setViewText(v, text)
            // если нужный нам TextView, то разрисовываем
            if (v?.id == R.id.tvValue) {
                val i = text?.toIntOrNull()
                if (i!! < 0) {
                    v.setTextColor(RED)
                } else if (i > 0)
                    v.setTextColor(GREEN)
            }
        }

        override fun setViewImage(v: ImageView?, value: Int) {
            super.setViewImage(v, value)

            val currentColor = (v?.background as? ColorDrawable)?.color

            when (value) {
                negative -> {
                    v?.setBackgroundColor(RED)
                    v?.setOnClickListener {
                        if (currentColor == RED) {
                            v?.setBackgroundColor(BLUE)
                        } else if (currentColor == BLUE) {
                            v?.setBackgroundColor(RED)
                        }
                    }
                }
                positive -> {
                    v?.setBackgroundColor(GREEN)
                    v?.setOnClickListener {
                        v?.setBackgroundColor(Color.YELLOW)
                    }
                }
            }
        }






    }
}


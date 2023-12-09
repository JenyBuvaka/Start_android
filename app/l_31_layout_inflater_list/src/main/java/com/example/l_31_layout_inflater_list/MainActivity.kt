package com.example.l_31_layout_inflater_list

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var name = arrayListOf("Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
        "Костя", "Игорь","Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
        "Костя", "Игорь")
    private var position = arrayListOf("Программер", "Бухгалтер", "Программер",
        "Программер", "Бухгалтер", "Директор", "Программер", "Охранник","Программер", "Бухгалтер", "Программер",
        "Программер", "Бухгалтер", "Директор", "Программер", "Охранник")
    private var salary = arrayListOf(13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000,13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000)
    private  var colors = IntArray(2)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colors[0]= Color.parseColor("#559966CC")
        colors[1] = Color.parseColor("#55336699")

        val linLayout = findViewById<LinearLayout>(R.id.linLayout)
        val ltInflater = layoutInflater

        for (i in name.indices){
            Log.d("myLogs", "i = " + i)
            val item = ltInflater.inflate(R.layout.item,linLayout,false)
            val tvName = item.findViewById<TextView>(R.id.tvName)
            tvName.text = name[i]
            val tvPosition = item.findViewById<TextView>(R.id.tvPosition)
            tvPosition.text = "Должность: ${position[i]}"
            val tvSalary = item.findViewById<TextView>(R.id.tvSalary)
            tvSalary.text = "Оклад: ${salary[i]}"
            item.layoutParams.width = LayoutParams.MATCH_PARENT
            item.setBackgroundColor(colors[i % 2])
            Log.d("myLOG", "iiii = = =${i % 2}")
            linLayout.addView(item)

        }
    }
}
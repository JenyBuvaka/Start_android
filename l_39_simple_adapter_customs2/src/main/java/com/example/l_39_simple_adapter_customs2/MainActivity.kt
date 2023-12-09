package com.example.l_39_simple_adapter_customs2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    val ATTRIBUTE_NAME_TEXT="text"
    val ATTRIBUTE_NAME_PB="pb"
    val ATTRIBUTE_NAME_LL="ll"

    lateinit var lvSimple:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val load = arrayListOf(41, 48, 22, 35, 30, 67, 51, 88)

        val data = ArrayList<Map<String, Any>>(load.size)
        var m: Map<String, Any>

        for (i in 0 until load.size) {
            m = HashMap()
            m[ATTRIBUTE_NAME_TEXT] = "Day " + (i + 1) + ". Load: " + load[i] + "%"
            m[ATTRIBUTE_NAME_PB] = load[i]
            m[ATTRIBUTE_NAME_LL] = load[i]
            data.add(m)
        }
        // массив имен атрибутов, из которых будут читаться данные
        val from = arrayOf(ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB, ATTRIBUTE_NAME_LL)
        val to = intArrayOf(R.id.tvLoad, R.id.pbLoad, R.id.llLoad)

        val sAdapter = SimpleAdapter(this, data, R.layout.item, from, to)

        sAdapter.viewBinder = MyViewBinder()
        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter
    }
        inner class MyViewBinder:SimpleAdapter.ViewBinder{

            val red = resources.getColor(R.color.Red);
            val orange = getResources().getColor(R.color.Orange);
            val green = getResources().getColor(R.color.Green)

            override fun setViewValue(p0: View?, p1: Any?, p2: String?): Boolean {
                var i = 0
                when(p0?.id){
                    R.id.llLoad->{
                        i =  (p1 as? Int) ?:0
                        if (i<40) p0.setBackgroundColor(green) else
                            if (i<70) p0.setBackgroundColor(orange) else
                                p0.setBackgroundColor(red)
                        return true
                    }
                    R.id.pbLoad->{
                        i =  (p1 as? Int) ?:0
                    if (p0 is ProgressBar){
                        p0.progress=i
                    }
                        return true
                    }
                    else -> return false
                }
            }

        }
    }

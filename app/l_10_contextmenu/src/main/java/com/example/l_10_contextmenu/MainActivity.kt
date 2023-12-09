package com.example.l_10_contextmenu
//В этом уроке мы:

//- создадим контекстное меню

//Контекстное меню вызывается в Андроид длительным нажатием на каком-либо экранном компоненте.
//Обычно оно используется в списках, когда на экран выводится список однородных объектов (например письма в почт.ящике) и,
//чтобы выполнить действие с одним из этих объектов, мы вызываем контекстное меню для него. Но т.к. списки мы еще не проходили,
//сделаем пример попроще и будем вызывать контекстное меню для TextView.
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvColor:TextView
    private lateinit var tvSize:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSize=findViewById(R.id.tvSize)
        tvColor=findViewById(R.id.tvColor)

        // для tvColor и tvSize необходимо создавать контекстное меню
        registerForContextMenu(tvSize)
        registerForContextMenu(tvColor)
    }
    val MENU_COLOR_RED=1
    val MENU_COLOR_GREEN=2
    val MENU_COLOR_BLUE=3

    val MENU_SIZE_22=4
    val MENU_SIZE_26=5
    val MENU_SIZE_30=6
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when(v!!.id){
            R.id.tvColor->{
                menu?.add(0,MENU_COLOR_BLUE,0,"Blue")
                menu?.add(0,MENU_COLOR_GREEN,0,"Green")
                menu?.add(0,MENU_COLOR_RED,0,"Red")}
            R.id.tvSize->{
                menu?.add(0,MENU_SIZE_22,0,"22")
                menu?.add(0,MENU_SIZE_26,0,"26")
                menu?.add(0,MENU_SIZE_30,0,"30")
            }
//            Обратите внимание, что мы по ID определяем View, для которого вызвано контекстное
//                    меню и в зависимости от этого создаем определенное меню. Т.е. если
//                    контекстное меню вызвано для tvColor, то мы создаем меню с перечислением
//                    цветов, а если для tvSize – с размерами шрифта.
//
//                    В качестве ID пунктов мы использовали константы. Группировку и сортировку
//                    не используем, поэтому используем нули в качестве соответствующих параметров.
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // пункты для меню tvCOlor
            MENU_COLOR_RED->{
                tvColor.text="Text color = RED"
                tvColor.setTextColor(Color.RED)
            }
            MENU_COLOR_BLUE->{
                tvColor.text="Text color = BLUE"
                tvColor.setTextColor(Color.BLUE)
            }
            MENU_COLOR_GREEN->{
                tvColor.setTextColor(Color.GREEN)
                tvColor.text="Text color = GREEN"
            }
            //укты для меню tvSize
            MENU_SIZE_26->{
                tvSize.setTextSize(26F)
                tvSize.text = "Text size = 26"
            }
            MENU_SIZE_22->{
                tvSize.setTextSize(22F)
                tvSize.text="Text size = 22"
            }
            MENU_SIZE_30->{
                tvSize.setTextSize(30F)
                tvSize.text="Text size = 30"
            }

        }
        return super.onContextItemSelected(item)
    }
}
package com.example.l_15_simple_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // константы для ID пунктов меню
    val MENU_ALPHA_ID = 1
    val MENU_SCALE_ID = 2
    val MENU_TRANSLATE_ID = 3
    val MENU_ROTATE_ID = 4
    val MENU_COMBO_ID = 5

    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.tv)
        // регистрируем контекстное меню для компонента tv
        registerForContextMenu(tv)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when (v?.id) {
            R.id.tv -> {
                menu?.add(0, MENU_ALPHA_ID, 0, "alpha");
                menu?.add(0, MENU_SCALE_ID, 0, "scale");
                menu?.add(0, MENU_TRANSLATE_ID, 0, "translate");
                menu?.add(0, MENU_ROTATE_ID, 0, "rotate");
                menu?.add(0, MENU_COMBO_ID, 0, "combo");
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var anim: Animation? = null
        // определяем какой пункт был нажат
        when (item.itemId) {
            MENU_ALPHA_ID -> {
                // создаем объект анимации из файла anim/myalpha
                anim = AnimationUtils.loadAnimation(this, R.anim.myalpha)
            }

            MENU_SCALE_ID -> {
                anim = AnimationUtils.loadAnimation(this, R.anim.myscale)
            }

            MENU_COMBO_ID -> {
                anim = AnimationUtils.loadAnimation(this, R.anim.mycombo)
            }

            MENU_ROTATE_ID -> {
                anim = AnimationUtils.loadAnimation(this, R.anim.myrotate)
            }

            MENU_TRANSLATE_ID -> {
                anim = AnimationUtils.loadAnimation(this, R.anim.mytrans)
            }
        }
        // запускаем анимацию для компонента tv
        tv.startAnimation(anim)
        return super.onContextItemSelected(item)
    }
}
//}Анимация читается из xml-файла методом AnimationUtils.loadAnimation, на выходе получается объект типа Animation.
//Его используем в методе startAnimation, который запускает анимацию.
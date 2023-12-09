package com.example.l_34_simple_list_events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.AdapterView


class MainActivity : AppCompatActivity() {
    val LOG_TAG="myLOGS"
    lateinit var lvMain:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvMain = findViewById(R.id.lvMain)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.names,
            android.R.layout.simple_expandable_list_item_1
        )
        lvMain.adapter = adapter

        lvMain.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->
            Log.d(LOG_TAG, "itemClick: position = $position, id = $id")
            //Смотрим код. Мы находим экранные элементы, создаем и присваиваем списку адаптер. Далее списку мы присваиваем два обработчика событий:
            //1) OnItemClickListener – обрабатывает нажатие на пункт списка
            //Предоставляет нам метод onItemClick(AdapterView<?> parent, View view, int position, long id), где
            //parent – View-родитель для нажатого пункта, в нашем случае - ListView
            //view – это нажатый пункт, в нашем случае – TextView из android.R.layout.simple_list_item_1
            //position – порядковый номер пункта в списке
            //id – идентификатор элемента,
            //Мы в лог будем выводить id и position для элемента, на который нажали.
        }
        lvMain.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d(LOG_TAG, "itemSelect: position = $position, id = $id")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(LOG_TAG, "itemSelect: nothing")

            }
            //2) OnItemSelectedListener – обрабатывает выделение пунктов списка (не check, как на прошлом уроке)
            //Предоставляет нам метод onItemSelected полностью аналогичен по параметрам методу onItemClick описанному выше. Не буду повторяться.
            //Также есть метод onNothingSelected – когда список теряет выделение пункта и ни один пункт не выделен.
        })
        lvMain.setOnScrollListener(object:AbsListView.OnScrollListener{
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                Log.d(LOG_TAG,"scrollState = "+scrollState)
            }

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
//                Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
//                        + ", visibleItemCount" + visibleItemCount
//                        + ", totalItemCount" + totalItemCount)
            }
        })
    }
    //OnScrollListener – обрабатывает прокрутку списка.
    //Методы:
    //1) onScrollStateChanged(AbsListView view, int scrollState) - обработка состояний прокрутки
    //view – это прокручиваемый элемент, т.е. ListView
    //scrollState – состояние списка. Может принимать три значения:
    //SCROLL_STATE_IDLE = 0, список закончил прокрутку
    //SCROLL_STATE_TOUCH_SCROLL = 1, список начал прокрутку
    //SCROLL_STATE_FLING = 2, список «катнули», т.е. при прокрутке отпустили палец и прокрутка дальше идет «по инерции»
    //Вывод в лог я пока закаментил, чтобы не мешалось. Чуть позже раскаментим.
    //2) onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) - обработка прокрутки
    //view – прокручиваемый элемент
    //firstVisibleItem – первый видимый на экране пункт списка
    //visibleItemCount – сколько пунктов видно на экране
    //totalItemCount – сколько всего пунктов в списке
    //Причем для параметров firstVisibleItem и visibleItemCount пункт считается видимым на экране даже если он виден не полностью.
    //Все сохраним и запустим.
    //Теперь потаскайте список туда-сюда курсором (как будто пальцем) и смотрите логи. Там слишком много всего выводится. Я не буду здесь выкладывать. Но принцип понятен – меняется первый видимый пункт (firstVisibleItem) и может на единицу меняться кол-во видимых пунктов (visibleItemCount).
}

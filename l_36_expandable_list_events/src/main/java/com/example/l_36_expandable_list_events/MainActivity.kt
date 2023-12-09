package com.example.l_36_expandable_list_events

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "myLogs"

    lateinit var elvMain:ExpandableListView
    lateinit var ah:AdapterHelper
    lateinit var adapter:SimpleExpandableListAdapter
    lateinit var tvInfo:TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById(R.id.tvInfo)

        //Создаем адаптер
        ah = AdapterHelper(this)
        adapter = ah.getAdapter()

        elvMain = findViewById(R.id.elvMain)
        elvMain.setAdapter(adapter)

        //Нажатия на элемент

        elvMain.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Log.d(LOG_TAG, "onChildClick groupPosition = " + groupPosition +
                    " childPosition = " + childPosition +
                    " id = " + id)

            tvInfo.text=ah.getGroupChildText(groupPosition,childPosition)
            return@setOnChildClickListener false
        }
        //Нажатие на группу
        elvMain.setOnGroupClickListener { parent, v, groupPosition, id ->
            Log.d(LOG_TAG, "onGroupClick groupPosition = " + groupPosition +
                    " id = " + id)
            // блокируем дальнейшую обработку события для группы с позицией 1

            if (groupPosition ==4) {
                return@setOnGroupClickListener true
            }
            return@setOnGroupClickListener false
        }
        //Сворачиваем группы
        elvMain.setOnGroupCollapseListener {
            Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + it)
            tvInfo.text=("Свернули"+ ah.getGroupText(it))
        }
        //Разворачиваем группы
        elvMain.setOnGroupExpandListener {
            Log.d(LOG_TAG, "onGroupExpand groupPosition = " + it)
            tvInfo.text = "Развернули ${ah.getGroupText(it)}"
        }
        //Разворачиваем группу в позицией 2
        elvMain.expandGroup(2)
    }
}
//Благодаря классу AdapterHelper, код создания адаптера занял всего две строчки: создание объекта и вызов метода getAdapter. Далее присваиваем адаптер списку и добавляем обработчики:
//
//1) OnChildClickListener - нажатие на элемент
//
//Метод
//public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id), где
//
//parent – ExpandableListView с которым работаем
//v – View элемента
//groupPosition – позиция группы в списке
//childPosition – позиция элемента в группе
//id – id элемента
//
//Мы выводим в лог позицию и id. А в TextView сверху от списка выводим текст нажатого элемента и его группы, который получаем с помощью методов AdapterHelper.
//
//Метод должен вернуть boolean. Если мы возвращаем true – это значит, мы сообщаем, что сами полностью обработали событие и оно не пойдет в дальнейшие обработчики (если они есть). Если возвращаем false – значит, мы позволяем событию идти дальше.
//
//
//
//2) OnGroupClickListener – нажатие на группу
//
//Метод
//public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id), где
//
//parent – ExpandableListView с которым работаем
//v – View элемента
//groupPosition – позиция группы в списке
//id – id группы
//
//Мы выводим в лог позицию и id группы.
//
//Этот метод также должен вернуть boolean. Мы будет возвращать true, если позиция группы = 1, иначе - false. Т.е. для этой группы мы блокируем дальнейшую обработку события. Далее увидим, что нам это даст.
//
//
//
//3) OnGroupCollapseListener – сворачивание группы
//
//Метод
//onGroupCollapse(int groupPosition), где groupPosition – позиция группы, которую свернули
//
//
//
//4) OnGroupExpandListener – разворачивание группы
//
//Метод
//onGroupExpand(int groupPosition), где groupPosition – позиция группы, которую развернули
//
//
//
//И в конце кода MainActivity мы разворачиваем группу с позицией 2, используя метод expandGroup.
//
//
//
//Все сохраним и запускаем.
//
//
//
//Как видим, группа LG сразу развернута. Это сработала команда expandGroup в конце кода.
//
//Если посмотреть в лог, то видим
//
//onGroupExpand groupPosition = 2
//
//Т.е. отработало событие разворачивания группы с позицией 2.
//
//
//
//Нажмем, например, на Optimus Link. Смотрим лог:
//
//onChildClick groupPosition = 2 childPosition = 1 id = 1
//
//Не забываем, что позиция считается с нуля. Группа с позицией 2 – LG, элемент с позицией 1 – Optimus Link, все верно.
//
//Смотрим TextView сверху экрана, он считал из адаптера значение атрибута и отобразил его.
//
//
//
//Теперь попробуем свернуть группу LG, нажмем на нее. Смотрим лог:
//
//onGroupClick groupPosition = 2 id = 2
//onGroupCollapse groupPosition = 2
//
//Сначала отработал onGroupClick – нажатие на группу, а потом onGroupCollapse – сворачивание группы. TextView наверху экрана оповестил о том, что свернули группу LG.
//
//
//
//Снова развернем группу LG. Лог:
//
//onGroupClick groupPosition = 2 id = 2
//onGroupExpand groupPosition = 2
//
//Нажатие на группу и разворачивание. Обратите внимание, что при программном разворачивании, события нажатия не было, только разворот.
//
//
//
//Теперь попробуем развернуть группу с позицией 1 – Samsung. Группа не разворачивается. Смотрим лог:
//
//onGroupClick groupPosition = 1 id = 1
//
//Событие нажатия есть, а вот обработчик разворачивания не вызывается. Это происходит из-за строчки
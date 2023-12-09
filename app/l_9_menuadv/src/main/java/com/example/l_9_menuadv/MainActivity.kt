package com.example.l_9_menuadv
//Ссылка на эту тему для создание меня кодом и через лайаут файл https://startandroid.ru/ru/uroki/vse-uroki-spiskom/46-urok-14-menju-gruppy-porjadok.html
//В этом уроке мы:
//
//- создаем пункты меню c ID
//- группируем и сортируем пункты меню
//
//На прошлом уроке мы рассмотрели простейший способ создания меню методом add(CharSequence title),
//на вход мы подавали только текст. Рассмотрим другую реализацию этого метода - add(int groupId,
//int itemId, int order, CharSequence title). У этого метода 4 параметра на вход:
//
//- groupId - идентификатор группы, частью которой является пункт меню
//- itemId - ID пункта меню
//- order - для задания последовательности показа пунктов меню
//- title - текст, который будет отображен
//
//Чтоб показать как используются все эти параметры, создадим приложение.
//На экране будет TextView и CheckBox:
//- TextView будет отображать какой пункт меню был выбран
//- CheckBox будет определять показывать обычное меню или расширенное.
//Это будет реализовано с помощью групп меню.
//
//Сразу уточню, понятия "обычное" и "расширенное" - это не Андроид-понятия, а просто мои названия.
//Т.е. когда запущено приложение и пользователь жмет кнопку меню, он видит "обычное" меню.
//Если же он включит CheckBox, то будет отображаться "расширенное" меню, в котором больше пунктов.
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tv:TextView
    private lateinit var chb:CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv=findViewById(R.id.textView)
        chb=findViewById(R.id.chbExtMenu)
    }
    // создание меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // добавляем пункты меню
        if (menu!=null){
        menu.add(0, 1, 0, "add")
        menu.add(0, 2, 0, "edit")
        menu.add(0, 3, 3, "delete")
        menu.add(1, 4, 1, "copy")
        menu.add(1, 5, 2, "paste")
        menu.add(1, 6, 4, "exit")}
        return super.onCreateOptionsMenu(menu)
    }
    // обновление меню
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
        menu?.setGroupVisible(1,chb.isChecked)
        return super.onPrepareOptionsMenu(menu)
    }
    // обработка нажатий
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sb = StringBuilder()
        // Выведем в TextView информацию о нажатом пункте меню
        sb.append("Item Menu");
        sb.append("\r\n groupId: ${item.groupId}")
        sb.append("\r\n itemId:  ${item.itemId}")
        sb.append("\r\n order: ${item.order}")
        sb.append("\r\n title: ${item.title}")
        tv.text=sb.toString();
        return super.onOptionsItemSelected(item)
    }
}
//Давайте разбирать написанное. Мы используем следующие методы:
//onCreateOptionsMenu - вызывается только при первом показе меню. Создает меню и более не используется. Здесь мы добавляем к меню пункты.
//onPrepareOptionsMenu - вызывается каждый раз перед отображением меню. Здесь мы вносим изменения в уже созданное меню, если это необходимо
//onOptionsItemSelected - вызывается при нажатии пункта меню. Здесь мы определяем какой пункт меню был нажат.


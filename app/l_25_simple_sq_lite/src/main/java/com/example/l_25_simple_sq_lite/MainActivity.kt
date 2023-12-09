package com.example.l_25_simple_sq_lite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLog"
    private lateinit var dbHelper:DBHelper
    private lateinit var db:SQLiteDatabase
    private lateinit var cv:ContentValues
    private lateinit var name:String
    private lateinit var email:String
    private lateinit var id:String
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etID = findViewById<EditText>(R.id.etID)

//        cv = ContentValues()
//        name = etName.text.toString()
//        email = etEmail.text.toString()
//        id = etID.text.toString()

        dbHelper = DBHelper(this)
        // подключаемся к БД
        db = dbHelper.getWritableDatabase()
        //SQLiteDatabase. Он позволит нам работать с БД. Мы будем использовать его методы insert – вставка записи, query – чтение, delete – удаление.

        val btnUpd = findViewById<Button>(R.id.btnUpd)
        btnUpd.setOnClickListener {
            cv = ContentValues()
            name = etName.text.toString()
            email = etEmail.text.toString()
            id = etID.text.toString()
            if(id.isNullOrEmpty()){
                return@setOnClickListener
            }
            Log.d(LOG_TAG, "--- Update mytable: ---")
            // подготовим значения для обновления
            cv.put("name", name);
            cv.put("email", email);
            // обновляем по id
            // Для этого используется метод update. На вход ему подается имя таблицы, заполненный ContentValues с значениями для обновления,
            // строка условия (Where) и массив аргументов для строки условия
            val updCount = db.update("mytable", cv, "id = ?", arrayOf(id))
            //В строке условия я использовал знак ?. При запросе к БД вместо этого знака будет подставлено значение из массива аргументов,
            // в нашем случае это – значение переменной id. Если знаков ? в строке условия несколько, то им будут сопоставлены значения из массива по порядку.
            // Метод update возвращает нам кол-во обновленных записей, которое мы выводим в лог.
            Log.d(LOG_TAG, "updated rows count = " + updCount)
        }
        val btnDel = findViewById<Button>(R.id.btnDel)
        btnDel.setOnClickListener {
            cv = ContentValues()
            name = etName.text.toString()
            email = etEmail.text.toString()
            id = etID.text.toString()
            if (id.isNullOrEmpty()){
                return@setOnClickListener
            }
            Log.d(LOG_TAG, "--- Delete from mytable: ---")
            // удаляем по id
            val delCount = db.delete("mytable", "id = "+id,null)
            Log.d(LOG_TAG, "deleted rows count = " + delCount)

            
        }
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            //создаем обьект для данных
            cv = ContentValues()
            name = etName.text.toString()
            email = etEmail.text.toString()
            id = etID.text.toString()
            //Класс ContentValues используется для указания полей таблицы и значений, которые мы в эти поля будем вставлять
            // получаем данные из полей ввода
            //val name = etName.text.toString()
            //val email = etEmail.text.toString()
            Log.d(LOG_TAG, "--- Insert in mytable: ---");
            // подготовим данные для вставки в виде пар: наименование столбца - значение
            cv.put("name",name)
            cv.put("email",email)
            // вставляем запись и получаем ее ID
            val rowID = db.insert("mytable",null,cv)
            Log.d(LOG_TAG, "row inserted, ID = " + rowID)
        }
        val btnRead = findViewById<Button>(R.id.btnRead)
        btnRead.setOnClickListener {
            Log.d(LOG_TAG, "--- Rows in mytable: ---")
            // делаем запрос всех данных из таблицы mytable, получаем Cursor
            val c:Cursor = db.query("mytable",null,null,null,null,null,null)
            // ставим позицию курсора на первую строку выборки
            // если в выборке нет строк, вернется false
            if (c.moveToFirst()){
                // определяем номера столбцов по имени в выборке
                val idColIndex = c.getColumnIndex("id")
                val nameColIndex = c.getColumnIndex("name")
                val emailColIndex = c.getColumnIndex("email")
               // tv.text = "ID = ${c.getInt(idColIndex)} \nname = ${c.getString(nameColIndex)}\nemail  ${c.getString(emailColIndex)}"
                do {
                    Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", email = " + c.getString(emailColIndex))

                    // переход на следующую строку
                    // а если следующей нет (текущая - последняя), то false - выходим из цикла
                }while (c.moveToNext())

            }else Log.d(LOG_TAG, "0 rows")
            c.close()
        }
        val btnClear = findViewById<Button>(R.id.btnClear)
        btnClear.setOnClickListener {
            Log.d(LOG_TAG, "--- Clear mytable: ---")
            // удаляем все записи
            val clearCount = db.delete("mytable", null, null)
// Сброс счетчика автоинкрементирования id
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'mytable'")
            Log.d(LOG_TAG, "deleted rows count = $clearCount")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}
class DBHelper(context: Context): SQLiteOpenHelper(context,"myDB",null,1) {

    @SuppressLint("RestrictedApi")
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db?.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "email text" + ");")
    }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }
}

package com.example.l_26_sqlitequery

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.PackageManagerCompat
import androidx.core.content.PackageManagerCompat.LOG_TAG

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "myLogs";
//Три массива данных name, people, region. Это наименования стран, их население (в млн.) и регионы, к которым страны относятся.
// По этим данным мы будем заполнять таблицу.
    private val name = arrayListOf(
        "Китай", "США", "Бразилия", "Россия", "Япония",
        "Германия", "Египет", "Италия", "Франция", "Канада"
    )
    private val people = arrayListOf(1400, 311, 195, 142, 128, 82, 80, 60, 66, 35)
    private val region = arrayListOf(
        "Азия", "Америка", "Америка", "Европа", "Азия",
        "Европа", "Африка", "Европа", "Европа", "Америка"
    )
    private lateinit var dbHelper: DBHelper
    private lateinit var db: SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         var colums: Array<String>?
         var selectionArgs: Array<String>?
         var selection: String?
         var groupBy: String?
         var having: String?
         var orderBy: String? = null

         var c: Cursor

        val etPeople = findViewById<EditText>(R.id.etPeople)
        val etRegionPeople = findViewById<EditText>(R.id.etRegionPeople)
        val etFunc = findViewById<EditText>(R.id.etFunc)

        val rgShort = findViewById<RadioGroup>(R.id.rgSort)

        dbHelper = DBHelper(this)
        //подключаемся к базе данных
        db = dbHelper.writableDatabase

        //проверка существования записей
         c=db.query("mytable", null, null, null, null, null, null)
        if (c!!.count == 0) {
            val cv = ContentValues()
            //заполним таблицу
            for (i in 0 until 10) {
                cv.put(" name ", name[i]);
                cv.put(" people ", people[i])
                cv.put(" region ", region[i])
                Log.d(LOG_TAG, " id = " + db.insert("mytable", null, cv))
            }
        }
        val btnAll = findViewById<Button>(R.id.btnAll)
        btnAll.setOnClickListener {
            Log.d(LOG_TAG, "--- Все записи ---")
            c = db.query("mytable", null, null, null, null, null, null)
            c.use { worckCursor(c) }
        }
//функция
        val btnFunc = findViewById<Button>(R.id.btnFunc)
        btnFunc.setOnClickListener {
            Log.d(LOG_TAG, "--- Функция " + etFunc.text.toString() + " ---")
            colums = arrayOf(etFunc.text.toString())
            c = db.query("mytable", colums, null, null, null, null, null)
            c.use { worckCursor(c) }
        }
        //Населения больше чем
        val btnPeople = findViewById<Button>(R.id.btnPeople)
        btnPeople.setOnClickListener {
            Log.d(LOG_TAG, "--- Население больше " + etPeople.text.toString() + " ---")
            selection = "people > ?"
            selectionArgs = arrayOf(etPeople.text.toString())
            c = db.query("mytable", null, selection, selectionArgs, null, null, null)
            c.use { worckCursor(c) }
        }
//Насиление по региону
        val btnGroup = findViewById<Button>(R.id.btnGroup)
        btnGroup.setOnClickListener {
            Log.d(LOG_TAG, "--- Население по региону ---")
            colums = arrayOf("region", "sum(people) as people")
            groupBy = "region"
            c = db.query("mytable", colums, null, null, groupBy, null, null)
            c.use { worckCursor(c) }
        }
        val btnHaving = findViewById<Button>(R.id.btnHaving)
        btnHaving.setOnClickListener {
            Log.d(
                LOG_TAG, "--- Регионы с населением больше " + etRegionPeople.text.toString()
                        + " ---"
            )
            colums = arrayOf("region", "sum(people) as people")
            groupBy = "region"
            having = "sum(people) > " + etRegionPeople.text.toString()
            c = db.query("mytable", colums, null, null, groupBy, having, null, null)
            c.use { worckCursor(c) }
        }
        //СОРТИРОВКА
        val btnShort = findViewById<Button>(R.id.btnSort)
        btnShort.setOnClickListener {
            //Сортировка по
            Log.d(LOG_TAG, "ggggggg")
            when (rgShort.checkedRadioButtonId) {
                R.id.rName -> {
                    Log.d(LOG_TAG, "--- Сортировка по наименованию ---")
                    orderBy = "name"
                }

                R.id.rPeople -> {
                    Log.d(LOG_TAG, "--- Сортировка по населению ---")
                    orderBy = "people"
                }

                R.id.rRegion -> {
                    Log.d(LOG_TAG, "--- Сортировка по региону ---");
                    orderBy = "region"
                }
            }
            c = db.query("mytable", null, null, null, null, null, orderBy)
            worckCursor(c)
            c.close()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
    @SuppressLint("RestrictedApi", "Range")
    fun worckCursor(c: Cursor){
        val et = findViewById<TextView>(R.id.tv)
        if (c.moveToFirst()) {
            var str: String
            do {
                str = ""
                for (cn in c.columnNames) {
                    str += "$cn = ${c.getString(c.getColumnIndex(cn))};\n  "
                }
                Log.d(PackageManagerCompat.LOG_TAG, str)
                et.text = str
            } while (c.moveToNext())
        }
        c.close()
        c.use {  }
        et.text = "Cursor is null"
    }
}

class DBHelper(context:Context) : SQLiteOpenHelper(context,"myDB",null,1){
    @SuppressLint("RestrictedApi")
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(LOG_TAG, "--- onCreate database ---")
        // создаем таблицу с полями
        db?.execSQL("create table mytable (" +
                "id integer primary key autoincrement," +
                "name text," +
                "people integer," +
                "region text" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}


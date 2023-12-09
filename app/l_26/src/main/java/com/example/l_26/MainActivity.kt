package com.example.l_26


import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.content.PackageManagerCompat.LOG_TAG

class MainActivity : Activity() {

    private val LOG_TAG = "myLogs"

    private val name = arrayOf(
        "Китай", "США", "Бразилия", "Россия", "Япония",
        "Германия", "Египет", "Италия", "Франция", "Канада"
    )
    private val people = intArrayOf(1400, 311, 195, 142, 128, 82, 80, 60, 66, 35)
    private val region = arrayOf(
        "Азия", "Америка", "Америка", "Европа", "Азия",
        "Европа", "Африка", "Европа", "Европа", "Америка"
    )

    private lateinit var btnAll: Button
    private lateinit var btnFunc: Button
    private lateinit var btnPeople: Button
    private lateinit var btnSort: Button
    private lateinit var btnGroup: Button
    private lateinit var btnHaving: Button
    private lateinit var etFunc: EditText
    private lateinit var etPeople: EditText
    private lateinit var etRegionPeople: EditText
    private lateinit var rgSort: RadioGroup
    private lateinit var dbHelper: DBHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAll = findViewById(R.id.btnAll)
        btnFunc = findViewById(R.id.btnFunc)
        btnPeople = findViewById(R.id.btnPeople)
        btnSort = findViewById(R.id.btnSort)
        btnGroup = findViewById(R.id.btnGroup)
        btnHaving = findViewById(R.id.btnHaving)
        etFunc = findViewById(R.id.etFunc)
        etPeople = findViewById(R.id.etPeople)
        etRegionPeople = findViewById(R.id.etRegionPeople)
        rgSort = findViewById(R.id.rgSort)

        dbHelper = DBHelper(this)
        db = dbHelper.writableDatabase

        val c = db.query("mytable", null, null, null, null, null, null)
        if (c.count == 0) {
            val cv = ContentValues()
            for (i in 0 until 10) {
                cv.put("name", name[i])
                cv.put("people", people[i])
                cv.put("region", region[i])
                Log.d(LOG_TAG, "id = ${db.insert("mytable", null, cv)}")
            }
        }
        c.close()
        dbHelper.close()

        btnAll.setOnClickListener { onClick(btnAll) }
        btnFunc.setOnClickListener { onClick(btnFunc) }
        btnPeople.setOnClickListener { onClick(btnPeople) }
        btnSort.setOnClickListener { onClick(btnSort) }
        btnGroup.setOnClickListener { onClick(btnGroup) }
        btnHaving.setOnClickListener { onClick(btnHaving) }
    }

    @SuppressLint("Range")
    private fun onClick(v: View) {
        db = dbHelper.writableDatabase
        val sFunc = etFunc.text.toString()
        val sPeople = etPeople.text.toString()
        val sRegionPeople = etRegionPeople.text.toString()
        var columns: Array<String>?
        var selection: String?
        var selectionArgs: Array<String>?
        var groupBy: String?
        var having: String?
        var orderBy: String? = null
        var c: Cursor? = null

        when (v.id) {
            R.id.btnAll -> {
                Log.d(LOG_TAG, "--- Все записи ---")
                c = db.query("mytable", null, null, null, null, null, null)
            }
            R.id.btnFunc -> {
                Log.d(LOG_TAG, "--- Функция $sFunc ---")
                columns = arrayOf(sFunc)
                c = db.query("mytable", columns, null, null, null, null, null)
            }
            R.id.btnPeople -> {
                Log.d(LOG_TAG, "--- Население больше $sPeople ---")
                selection = "people > ?"
                selectionArgs = arrayOf(sPeople)
                c = db.query("mytable", null, selection, selectionArgs, null, null, null)
            }
            R.id.btnGroup -> {
                Log.d(LOG_TAG, "--- Население по региону ---")
                columns = arrayOf("region", "sum(people) as people")
                groupBy = "region"
                c = db.query("mytable", columns, null, null, groupBy, null, null)
            }
            R.id.btnHaving -> {
                Log.d(LOG_TAG, "--- Регионы с населением больше $sRegionPeople ---")
                columns = arrayOf("region", "sum(people) as people")
                groupBy = "region"
                having = "sum(people) > $sRegionPeople"
                c = db.query("mytable", columns, null, null, groupBy, having, null)
            }
            R.id.btnSort -> {
                when (rgSort.checkedRadioButtonId) {
                    R.id.rName -> {
                        Log.d(LOG_TAG, "--- Сортировка по наименованию ---")
                        orderBy = "name"
                    }
                    R.id.rPeople -> {
                        Log.d(LOG_TAG, "--- Сортировка по населению ---")
                        orderBy = "people"
                    }
                    R.id.rRegion -> {
                        Log.d(LOG_TAG, "--- Сортировка по региону ---")
                        orderBy = "region"
                    }
                }
                c = db.query("mytable", null, null, null, null, null, orderBy)
            }
        }

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    var str = ""
                    for (cn in c.columnNames) {
                        str = "$str$cn = ${c.getString(c.getColumnIndex(cn))}; "
                    }
                    Log.d(LOG_TAG, str)
                } while (c.moveToNext())
            }
            c.close()
        } else {
            Log.d(LOG_TAG, "Cursor is null")
        }

        dbHelper.close()
    }
}

class DBHelper(context: Context) : SQLiteOpenHelper(context, "myDB", null, 1) {

    @SuppressLint("RestrictedApi")
    override fun onCreate(db: SQLiteDatabase) {
        Log.d(LOG_TAG, "--- onCreate database ---")
        db.execSQL(
            "create table mytable ("
                    + "id integer primary key autoincrement," + "name text,"
                    + "people integer," + "region text" + ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}
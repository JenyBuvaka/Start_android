package com.example.l_29_sqliteonupgradedb

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        const val LOG_TAG = "myLogs"

        const val DB_NAME = "staff" // имя БД

        const val DB_VERSION = 2 // версия БД
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbh = DBHelper(this)
        val db: SQLiteDatabase = dbh.writableDatabase
        Log.d(LOG_TAG, " --- Staff db v." + db.version + " --- ")
        writeStaff(db)
        dbh.close()
    }

    // запрос данных и вывод в лог
    //Метод writeStaff – выбирает данные из таблицы people и вызывает метод для вывода данных в лог.
    private fun writeStaff(db: SQLiteDatabase) {
        var c = db.rawQuery("select * from people", null)
        logCursor(c, "Table people")
        c.close()

        c = db.rawQuery("select * from position", null)
        logCursor(c, "Table position")
        c.close()

        val sqlQuery = ("select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id ")
        c = db.rawQuery(sqlQuery, null)
        logCursor(c, "inner join")
        c.close()

    }

    // вывод в лог данных из курсора
    @SuppressLint("Range")
    fun logCursor(c: Cursor?, title: String) {
        if (c != null) {
            if (c.moveToFirst()) {
                Log.d(LOG_TAG, title + ". " + c.count + " rows")
                val sb = StringBuilder()
                do {
                    sb.setLength(0)
                    for (cn: String in c.columnNames)
                    //Фактически, cn - это просто временная переменная, которая перебирает все имена столбцов в
                    // текущей записи курсора, чтобы получить их значения и добавить их к строке вывода в лог.
                    {
                        sb.append(
                            cn + " = "
                                    + c.getString(c.getColumnIndex(cn)) + "; "
                        )
                            //Log.d(LOG_TAG, "что тут происходит =)) $cn  and $sb")

                    }
                    Log.d(LOG_TAG, sb.toString())
                } while (c.moveToNext())
            }
        } else Log.d(LOG_TAG, "$title. Cursor is null")
    }

    // класс для работы с БД
    internal class DBHelper(context: Context?) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            Log.d(LOG_TAG, " --- onCreate database --- ")
            val position_salary = arrayOf(15000, 13000, 10000, 8000)
            val people_posid= arrayOf(2, 3, 2, 2, 3, 1, 2, 4)
            val position_id = arrayOf(1, 2, 3, 4 )
            val people_name = arrayOf(
                "Иван", "Марья", "Петр", "Антон", "Даша",
                "Борис", "Костя", "Игорь"
            )
            val position_name = arrayOf("Директор", "Программер", "Бухгалтер",
                "Охранник")
            val cv = ContentValues()

            // создаем таблицу должностей
            db.execSQL("create table position (" + "id integer primary key,"
                    + "name text, salary integer" + ");")
            //заполняем её
            for (i in position_id.indices){
                cv.clear()
                cv.put("id",position_id[i])
                cv.put("name",position_name[i])
                cv.put("salary",position_salary[i])
                db.insert("position",null,cv)
            }
            // создаем таблицу людей
            db.execSQL(
                "create table people ("
                        + "id integer primary key autoincrement,"
                        + "name text, posid integer);")
            // заполняем ее
            for (i in people_name.indices) {
                cv.clear()
                cv.put("name", people_name[i])
                cv.put("posid", people_posid[i])
                db.insert("people", null, cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            //План обновления такой:
            //
            //- создаем и заполняем данными таблицу position
            //- добавляем в таблицу people столбец – posid для хранения id из position
            //- заполняем people.posid данными из position в зависимости от значения people.position
            //- удаляем столбец people.position
            Log.d(
                LOG_TAG, " --- onUpgrade database from " + oldVersion
                        + " to " + newVersion + " version --- "
            )
            if (oldVersion == 1 && newVersion == 2) {
                val cv = ContentValues()
                //данные таблицы должностей
                val position_id = arrayOf(1, 2, 3, 4)
                val position_name = arrayOf(
                    "Директор", "Программер",
                    "Бухгалтер", "Охранник"
                )
                val position_salary = arrayOf(15000, 13000, 10000, 8000)
                db.beginTransaction()
                try {
                    // создаем таблицу должностей
                    db.execSQL(
                        "create table position ("
                                + "id integer primary key,"
                                + "name text, salary integer);"
                    )
                    //заполняем её
                    for (i in position_id.indices) {
                        cv.clear()
                        cv.put("id", position_id[i])
                        cv.put("name", position_name[i])
                        cv.put("salary", position_salary[i])
                        db.insert("position", null, cv)
                    }
                    db.execSQL("alter table people add column posid integer;")

                    for (i in position_id.indices) {
                        cv.clear()
                        cv.put("posid", position_id[i])
                        db.update("people", cv, "position=?", arrayOf(position_name[i]))
                    }
                    db.execSQL(
                        "create temporary table people_tmp ("
                                + "id integer, name text, position text, posid integer);"
                    )

                    db.execSQL("insert into people_tmp select id, name, position, posid from people;")
                    db.execSQL("drop table people;")

                    db.execSQL(
                        "create table people ("
                                + "id integer primary key autoincrement,"
                                + "name text, posid integer);"
                    )

                    db.execSQL("insert into people select id, name, posid from people_tmp;")
                    db.execSQL("drop table people_tmp;")

                    db.setTransactionSuccessful()
                }finally {
                    db.endTransaction()
                }
            }
        }
    }
}




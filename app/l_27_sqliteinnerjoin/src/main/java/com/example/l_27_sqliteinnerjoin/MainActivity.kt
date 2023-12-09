package com.example.l_27_sqliteinnerjoin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG

  private val LOG_TAG = "myLog"
// данные для таблицы должностей
  private var position_id = arrayListOf(1,2,3,4)
  private var  position_name = arrayListOf("Директор", "Программер", "Бухгалтер", "Охранник")
  private var position_salary = arrayListOf(15000, 13000, 10000, 8000)
// данные для таблицы людей
  private var people_name = arrayListOf("Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь")
  private var people_posid = arrayListOf(2, 3, 2, 2, 3, 1, 2, 4)
  private var dbh: DBHelper? = null

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Подключаемся к БД
         dbh = DBHelper(this)
        val  db = dbh?.writableDatabase

        //Описание курсора
        var c:Cursor
        //// выводим в лог данные по должностям
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- Table position ---")
        c = db!!.query("position",null,null,null,null,null,null)
        c.use {
            logCursor(c)
        }
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- ---")
        // выводим в лог данные по людям
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- Table people ---")
        c = db.query("people", null, null, null, null, null, null)
        c.use {
            logCursor(c)
        }
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- ---")
        // выводим результат объединения
        // используем rawQuery
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- INNER JOIN with rawQuery---")
        val sqlQuery = ("select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?")
        c = db.rawQuery(sqlQuery, arrayOf("12000"))
        c.use {
            logCursor(c)
        }
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- ---")
        // выводим результат объединения
        // используем query
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- INNER JOIN with query---")
        val table = "people as PL inner join position as PS on PL.posid = PS.id"
        val columns = arrayOf( "PL.name as Name", "PS.name as Position", "salary as Salary" )
        val selection = "salary < ?"
        val selectionArgs = arrayOf("12000")//почему значение задаётся в виде массива???а не просто стрингой
        c = db.query(table, columns, selection, selectionArgs, null, null, null)
        c.use {
            logCursor(c)
        }
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- ---")
    }

    override fun onDestroy() {
        super.onDestroy()
        dbh?.close()
    }

}
class DBHelper(context: Context):SQLiteOpenHelper(context,"myDB",null,1){
    @SuppressLint("RestrictedApi")
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "--- onCreate database ---")
        val cv = ContentValues()
        // создаем таблицу должностей
        db?.execSQL("create table position ("
                +"id integer primary key,"
                +"name text,"
                +"salary integer"
                +")")
        //заполняем её
        for(i in 0 until position_id.size){
//            cv.clear()
            cv.put("id", position_id[i])
            cv.put("name", position_name[i])
            cv.put("salary", position_salary[i])
            db?.insert("position",null,cv)
            //"position": Это имя таблицы, в которую вы хотите вставить данные. В данном случае, данные будут вставлены в таблицу с именем "position".
            //null: Это аргумент, который обычно используется для вставки пустых строк или для работы с нулевыми значениями в случае, если вставляемые данные имеют значение null. В данном случае, он означает, что вставлять значения в столбцы, имеющие значение null, не требуется.
            //cv: Это объект ContentValues, который содержит пары "ключ-значение" данных, которые вы хотите вставить в таблицу. Каждый ключ соответствует имени столбца в таблице, а значение представляет собой данные, которые вы хотите вставить в этот столбец.
        }
        // создаем таблицу людей
        db?.execSQL("create table people ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "posid integer"
                + ");")
        //заполняем её
        for (i in 0 until people_name.size){
            cv.clear()
            cv.put("name", people_name[i])
            cv.put("posid", people_posid[i])
            db?.insert("people", null, cv)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
// вывод в лог данных из курсора
@SuppressLint("Range", "RestrictedApi")
fun logCursor(c:Cursor){
    if(c.moveToFirst()) {
        var str: String
        do {
            str = ""
            for (cn in c.columnNames) {
                str += ("$cn = ${c.getString(c.getColumnIndex(cn))};")
            }
            Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, str)
        } while (c.moveToNext())
    }else Log.d(com.example.l_27_sqliteinnerjoin.LOG_TAG, "Cursor is null")
}
package com.example.l_28_sqlitetransaction

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG
import java.nio.file.Files.delete


class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    private lateinit var dbh:DBHelper
    private lateinit var db:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "--- onCreate Activity ---");
        dbh =  DBHelper(this);
        myActions()

    }

    private fun myActions() {
        db = dbh.getWritableDatabase()
        //read write
        //В чем разница между getWritableDatabase и getReadableDatabase? Судя по хелпу, в обычной ситуации оба метода возвращают одно и то же.
        // И оба позволят читать и менять БД. В случае же, например, проблемы отсутствия свободного места на устройстве, метод getReadableDatabase вернет
        // БД только для чтения, а getWritableDatabase выдаст ошибку

        delete(db, "mytable")
        db.beginTransaction()
        insert(db, "mytable", "val1")
        db.setTransactionSuccessful()//подтверждаем успешность транзакции методом setTransactionSuccessfu
        insert(db, "mytable","val2")
        db.endTransaction()
        insert(db,"mytable","val3")
        read(db,"mytable")
        //close
        //Метод close есть и у SQLiteDatabase и у SQLiteOpenHelper. Какая между ними разница? Каким из них пользоваться для закрытия подключения?
        //Тут надо понимать один момент – объект SQLiteOpenHelper всегда предоставляет только одно подключение. Попробую объяснить этот механизм.
        // У объекта SQLiteOpenHelper есть внутренний атрибут mDatabase типа SQLiteDatabase. Когда мы вызываем метод getWritableDatabase,
        // объект SQLiteOpenHelper проверяет: если mDatabase не null и не закрыт, то он и идет в качестве return. Иначе  SQLiteOpenHelper выполняет
        // подключение к БД, записывает новый SQLiteDatabase-объект в mDatabase и возвращает нам его. Т.е. метод getWritableDatabase либо возвращает существующее
        // подключение к БД, либо создает новое в случае отсутствия подключения. Когда же выполняется метод close для SQLiteOpenHelper, то происходит вызов close
        // для mDatabase и выполняется код mDatabase = null
        dbh.close()
        //Мы подключаемся к базе, чистим таблицу, открываем транзакцию методом beginTransaction, вставляем val1, закрываем транзакцию
    // методом endTransaction, вставляем val2, выводим содержимое в лог и отключаемся. Все сохраняем, запускаем и смотрим лог:
        // рекомендуемая форма для использования транзакций такая:
        //db.beginTransaction();
        //try {
        //  ...
        //  db.setTransactionSuccessful();
        //} finally {
        //  db.endTransaction(); }
        //Это очень важно! Т.е. если вы открыли транзакцию, выполнили какие-либо действия и не закрыли транзакцию, то все операции будут считаться неуспешными и
    // изменения не будут внесены в БД. Поэтому закрытие транзакции необходимо выполнять и finally нам это гарантирует
    }
    // етод insert – набор операций для вставки записи
    private fun insert(db:SQLiteDatabase,table:String,value:String){
        Log.d(LOG_TAG, "Insert in table " + table + " value = " + value);
        val cv = ContentValues();
        cv.put("val", value);
        db.insert(table, null, cv)
    }
    // Метод read – чтение всех записей
    @SuppressLint("Range")
    private fun read(db:SQLiteDatabase, table:String){
        Log.d(LOG_TAG, "Read table $table")
        val c = db.query(table, null, null, null, null, null, null)
        if (c != null) {
            Log.d(LOG_TAG, "Records count = " + c.count)
            if (c.moveToFirst()) {
                do {
                    Log.d(LOG_TAG, c.getString(c.getColumnIndex("val")))
                } while (c.moveToNext())
            }
            c.close()
        }
    }
    //Метод delete – удаление всех записей
    private fun delete(db:SQLiteDatabase,table:String){
        Log.d(LOG_TAG, "Delete all from table " + table)
        db.delete(table, null, null)
    }
    class DBHelper(context: Context):SQLiteOpenHelper(context,"myDB",null,1){
        @SuppressLint("RestrictedApi")
        override fun onCreate(db: SQLiteDatabase?) {
            Log.d(LOG_TAG, "--- onCreate database ---");

            db?.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "val text"
                    + ");")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }

    }
}
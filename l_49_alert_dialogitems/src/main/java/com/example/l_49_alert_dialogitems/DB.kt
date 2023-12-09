package com.example.l_49_alert_dialogitems

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class DB(context: Context) {
    private val DB_NAME = "mydb"
    private val DB_VERSION = 1
    private val DB_TABLE = "mytab"

    private val DB_CREATE = "create table " + DB_TABLE + "(" + COLUMN_ID + " integer primary key, " + COLUMN_TXT + " text " + ")"

    companion object{
        const val COLUMN_ID = "_id"
        const val COLUMN_TXT = "txt"
    }
    private val mCtx = context

    private lateinit var mDBHelper:DBHelper
    private lateinit var mDB:SQLiteDatabase

    fun open(){
        mDBHelper = DBHelper(mCtx,DB_NAME,null ,DB_VERSION)
        mDB = mDBHelper.writableDatabase
    }
    fun close(){
        mDBHelper.close()
    }
    // получить все данные из таблицы DB_TABLE
    fun  getAllData():Cursor{
        return mDB.query(DB_TABLE,null,null,null,null,null,null)
    }
    // изменить запись в DB_TABLE
    fun changeRec(id:Int,txt:String){
        val cv = ContentValues()
        cv.put(COLUMN_TXT,txt)
        mDB.update(DB_TABLE,cv, COLUMN_ID+ " = " + id,null)

    }

    inner class DBHelper(context: Context, name:String, factory: CursorFactory?, version:Int):
        SQLiteOpenHelper(context,name,factory,version) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(DB_CREATE)
            val cv = ContentValues()
            for (i in 1 until 5){
                cv.put(COLUMN_ID,i)
                cv.put(COLUMN_TXT,"sometext"+i)
                db?.insert(DB_TABLE,null,cv)
            }

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }

    }


}
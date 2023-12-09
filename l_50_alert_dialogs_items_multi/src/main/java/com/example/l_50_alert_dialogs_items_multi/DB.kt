package com.example.l_50_alert_dialogs_items_multi

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

    val COLUMN_ID = "_id"
    val COLUMN_CHK = "checked"
    val COLUMN_TXT = "txt"

    val DB_CREATE = "create table " +
            DB_TABLE + "(" +
            COLUMN_ID + " integer primary key, " +
            COLUMN_CHK + " integer, " +
            COLUMN_TXT + " text " +
            ");"

    private val mCtx = context

    private lateinit var mDBHelper:DBHelper
    private lateinit var mDB:SQLiteDatabase

    fun open(){
        mDBHelper = DBHelper(mCtx,DB_NAME,null,DB_VERSION)
        mDB = mDBHelper.getWritableDatabase()
    }

    fun close(){
        mDBHelper.close()
    }

    fun getAllData():Cursor{
        return mDB.query(DB_TABLE,null,null,null,null,null,null)
    }

    fun changeRec(position:Int,isCHeked:Boolean){
        val cv = ContentValues()
        cv.put(COLUMN_CHK, if(isCHeked) 1 else 0)
        mDB.update(DB_TABLE,cv,COLUMN_ID + " = " + (position + 1),null)
    }
    inner class DBHelper(context: Context, name: String, factory: CursorFactory?, version: Int):
        SQLiteOpenHelper(context,name,factory,version) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(DB_CREATE)

            val cv = ContentValues()
            for (i in 1 until 5) {
                cv.put(COLUMN_ID, i)
                cv.put(COLUMN_TXT,"sometext $i")
                cv.put(COLUMN_CHK,0)
                db?.insert(DB_TABLE,null,cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }

    }

}
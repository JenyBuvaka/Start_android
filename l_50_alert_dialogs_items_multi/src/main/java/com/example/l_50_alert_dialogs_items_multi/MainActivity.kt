package com.example.l_50_alert_dialogs_items_multi

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "myLogs"

    private val DIALOGS_ITEMS = 1
    private val DIALOG_CURSOR = 3

    lateinit var db:DB
    lateinit var cursor: Cursor

    private val data = arrayOf("one", "two", "three", "four")
    private val chkd = booleanArrayOf(false,true,true,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //open connect is DB
        db = DB(this)
        db.open()
        cursor = db.getAllData()
        startManagingCursor(cursor)

        val btnItem = findViewById<Button>(R.id.btnItems)
        btnItem.setOnClickListener {
            showDialog(DIALOGS_ITEMS)
        }
        val btnCursor = findViewById<Button>(R.id.btnCursor)
        btnCursor.setOnClickListener {
            showDialog(DIALOG_CURSOR)
        }
    }



    override fun onCreateDialog(id: Int): Dialog {
        val adb = AlertDialog.Builder(this)
        when(id){
            DIALOGS_ITEMS->{
                adb.setTitle(R.string.items)
                adb.setMultiChoiceItems(data,chkd,myItemsMultiClickListener)
            }
            DIALOG_CURSOR->{
                adb.setTitle(R.string.cursor)
                adb.setMultiChoiceItems(cursor,db.COLUMN_CHK,db.COLUMN_TXT,myCursorMultiClickListener)
            }
        }
        adb.setPositiveButton(R.string.ok,myBtnClickListener)
        return adb.create()
    }

    val myCursorMultiClickListener = OnMultiChoiceClickListener {dialog: DialogInterface?, which: Int, isChecked: Boolean ->
        val lv:ListView = (dialog as AlertDialog).listView
        Log.d(LOG_TAG,"which = $which isCheked = $isChecked")
        db.changeRec(which,isChecked)
        cursor.requery()
    }


    val myItemsMultiClickListener:OnMultiChoiceClickListener = OnMultiChoiceClickListener {dialog: DialogInterface?, which: Int, isChecked: Boolean ->
        Log.d(LOG_TAG,"which = $which, isCheked = $isChecked")
    }
    var myBtnClickListener = DialogInterface.OnClickListener { dialog, which ->
        val sbArray = (dialog as AlertDialog).listView.checkedItemPositions
        for (i in 0 until sbArray.size()) {
            val key = sbArray.keyAt(i)
            if (sbArray[key]) Log.d("qwe", "checked: $key")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}
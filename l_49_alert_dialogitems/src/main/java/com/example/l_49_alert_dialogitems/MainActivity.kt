package com.example.l_49_alert_dialogitems

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"

    companion object{
        const val DIALOG_ITEM = 1
        const val DIALOG_ADAPTER = 2
        const val DIALOG_CURSOR = 3
        var cnt = 0
    }
    lateinit var db:DB
    lateinit var cursor: Cursor

    private val data = arrayOf("one", "two", "three", "four")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //открываем подключение к БД
        db = DB(this)
        db.open()
        cursor = db.getAllData()
        startManagingCursor(cursor)

        val btnItems = findViewById<Button>(R.id.btnItems)
        btnItems.setOnClickListener {showDialog(DIALOG_ITEM)
        changeCount()}
        val btnAdapter = findViewById<Button>(R.id.btnAdapter)
        btnAdapter.setOnClickListener { showDialog(DIALOG_ADAPTER)
        changeCount()}
        val btnCursor = findViewById<Button>(R.id.btnCursor)
        btnCursor.setOnClickListener { showDialog(DIALOG_CURSOR)
        changeCount()}
    }

    override fun onCreateDialog(id: Int): Dialog {
        val adb = AlertDialog.Builder(this)
        when (id){
            DIALOG_ITEM->{
                adb.setTitle(R.string.items)
                adb.setItems(data,myClickListener)
            }
            DIALOG_ADAPTER->{
                adb.setTitle(R.string.adapter)
                val adapter = ArrayAdapter<String>(this,android.R.layout.select_dialog_item,data)
                adb.setAdapter(adapter,myClickListener)
            }
            DIALOG_CURSOR->{
                adb.setTitle(R.string.cursor)
                adb.setPositiveButton("Привет",dialogOnClickListener)
                adb.setCursor( cursor, myClickListener, DB.COLUMN_TXT)
            }
        }
        return adb.create()
    }

     @SuppressLint("SuspiciousIndentation")
     val dialogOnClickListener = DialogInterface.OnClickListener { dialog:DialogInterface, which:Int ->
    val adb = AlertDialog.Builder(this)
         when(which){
             Dialog.BUTTON_POSITIVE ->{
                 adb.setTitle("HAHA")
                 Toast.makeText(this,"hahha",Toast.LENGTH_LONG).show()
                 adb.create()
             }
         }
         cursor.requery()
         showDialog(DIALOG_CURSOR)
    }

    override fun onPrepareDialog(id: Int, dialog: Dialog?) {
//получаем доступ к адаптеру списка диалогов
        val aDialog = dialog as AlertDialog
        val lAdapter = aDialog.listView.adapter
        when(id){
            DIALOG_ITEM->{}
            DIALOG_ADAPTER->{
                //Проверка возможностей преоброзования
                if (lAdapter is BaseAdapter){
                    // преобразование и вызов метода-уведомления о новых данных
                    val bAdapter = lAdapter as BaseAdapter
                    bAdapter.notifyDataSetChanged()
                }
            }
            DIALOG_CURSOR->{}
        }
    }

    val myClickListener = DialogInterface.OnClickListener {dialog, which ->
    Log.d(LOG_TAG,"which = $which")

    }
    fun changeCount(){
        cnt++
        //Обновления массива
        data[3] = cnt.toString()
        //update DB
        db.changeRec(4, cnt.toString())
        cursor.requery()
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }


}

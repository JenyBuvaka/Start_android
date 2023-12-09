package com.example.l_47_alert_dialog_simple

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val DIALOG_EXIT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnExit)
        btn.setOnClickListener {
            showDialog(DIALOG_EXIT)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        if (id == DIALOG_EXIT){
            val adb = AlertDialog.Builder(this)
            //Title
            adb.setTitle(R.string.exit)
            //Message
            adb.setMessage(R.string.save_data)
            //Icon
            adb.setIcon(android.R.drawable.ic_dialog_info)
            //Button true answer(ответа)
            adb.setPositiveButton(R.string.yes,myClickListener)
            //Button false answer
            adb.setNegativeButton(R.string.no,myClickListener)
            //Button neutral answer
            adb.setNeutralButton(R.string.cancel,myClickListener)
            //create dialog
            return adb.show()
            //Чтобы диалог вызывался не только по кнопке выход, но и при нажатии на кнопку Назад в приложении, добавьте вызов диалога в реализацию метода onBackPressed
//А если хотите, чтобы вызванный диалог не закрывался по нажатию кнопки Назад, то используйте метод setCancelable:
//adb.setCancelable(false)
        }
        return super.onCreateDialog(id)
    }

    val myClickListener = DialogInterface.OnClickListener { dialog:DialogInterface,which:Int ->
        when(which){
            Dialog.BUTTON_POSITIVE->{
                saveData()
                finish()
            }
            Dialog.BUTTON_NEGATIVE->{
                finish()
            }
            Dialog.BUTTON_NEUTRAL->{

            }
        }

    }

    private fun saveData() {
Toast.makeText(this,R.string.saved,Toast.LENGTH_SHORT).show()
    }
}
package com.example.l_53_progress_dialog

import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

//ProgressDialog позволяет показать пользователю, что идет какая-либо операция и надо подождать. Он бывает двух видов: просто вращающийся круг и полоса-индикатор, которая показывает процент выполнения. Сделаем приложение, которое будет показывать оба вида.
class MainActivity : AppCompatActivity() {

    lateinit var pd:ProgressDialog
    lateinit var h:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDefault = findViewById<Button>(R.id.btnDefault)
        btnDefault.setOnClickListener {
            pd = ProgressDialog(this)
            pd.setTitle("Title")
            pd.setMessage("Message")
            //Добавляем кнопку
            pd.setButton(Dialog.BUTTON_POSITIVE, "OK") { dialog, which -> }
            pd.show()
        }
        val btnHorizont = findViewById<Button>(R.id.btnHoriz)
        btnHorizont.setOnClickListener{
            pd = ProgressDialog(this)
            pd.setTitle("Title")
            pd.setMessage("Message")
            //Меняем стиль на индикатор
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            //Устанавливаем максимум
            pd.max = 2148
            //Включаем анимацию ожидания
            pd.isIndeterminate = true
            pd.show()
            h = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    //Выключаем анимацтю ожидания
                    pd.isIndeterminate = false
                    if (pd.progress < pd.max) {
                        //Увеличиваем значение индикаторов
                        pd.incrementProgressBy(50)//Основной индикатор
                        pd.incrementSecondaryProgressBy(75)//Дополнительный индикатор
                        h.sendEmptyMessageDelayed(0, 100)
                    } else
                        pd.dismiss()
                    //Handler ждет 2 секунды (просто имитация, например, подключения к серверу), выключает анимацию ожидания (setIndeterminate), затем каджые 100 миллисекунд увеличивает значение основного (incrementProgressBy) и дополнительного (incrementSecondaryProgressBy) индикатора, пока основной индикатор (getProgress) не достигнет максимума (getMax, в нашем случае = 2148). После этого диалог закрываем (dismiss).
                }
            }
            h.sendEmptyMessageDelayed(0,2000)
        }

    }
    }

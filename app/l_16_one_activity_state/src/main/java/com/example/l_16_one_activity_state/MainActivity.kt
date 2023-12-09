package com.example.l_16_one_activity_state

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

//Теория
//При работе приложения, мы создаем новые Activity и закрываем старые, сворачиваем приложение, снова открываем и т.д. Activity умеет обрабатывать все эти движения. Это необходимо, например, для освобождения ресурсов или сохранения данных. В хелпе достаточно подробно это описано.

//Созданное при работе приложения Activity может быть в одном из трех состояний:

//Resumed - Activity видно на экране, оно находится в фокусе, пользователь может с ним взаимодействовать. Это состояние также иногда называют Running.
//Paused - Activity не в фокусе, пользователь не может с ним взаимодействовать, но его видно (оно перекрыто другим Activity, которое занимает не весь экран или полупрозрачно).
//Stopped - Activity не видно (полностью перекрывается другим Activity), соответственно оно не в фокусе и пользователь не может с ним взаимодействовать.
//Когда Activity переходит из одного состояния в другое, система вызывает различные его методы, которые мы можем заполнять своим кодом

//Для упрощения понимания я дал краткое описание состояний в скобках под названиями. А крестом обозначил отсутствие Activity.
//
//Итак, мы имеем следующие методы Activity, которые вызывает система:
//
//onCreate() – вызывается при первом создании Activity
//onStart() – вызывается перед тем, как Activity будет видно пользователю
//onResume() – вызывается перед тем как будет доступно для активности пользователя (взаимодействие)
//
//onPause() – вызывается перед тем, как будет показано другое Activity
//onStop() – вызывается когда Activity становится не видно пользователю
//onDestroy() – вызывается перед тем, как Activity будет уничтожено
//
//Т.е. эти методы НЕ вызывают смену состояния. Наоборот, смена состояния Activity является триггером,
// который вызывает эти методы. Тем самым нас уведомляют о смене, и мы можем реагировать соответственно.
// Посмотрим на практике, когда и в каком порядке вызываются эти методы.
class MainActivity : AppCompatActivity() {
    val TAG = "States"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"MainActivity: onCreate")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity: onDestroy()")
    }
}
// При реализации этих методов обязательно вызывайте соответствующие методы супер-класса и обязательно перед вашим кодом. См. код выше. Каждый метод содержит вызов метода супер-класса и свой код расположен после этих вызовов
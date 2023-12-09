package com.example.l_61_files

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedWriter
import java.io.OutputStreamWriter

//Работа с файлами в Android не сильно отличается от таковой в Java. В этом уроке рассмотрим, как записать/прочесть файл во внутреннюю память и на SD-карту.
class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    private val FILENAME = "file"
    private val DIR_SD = "MyFiles"
    private val FILENAME_SD = "fileSD"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun writeFile(){
        //Открываем поток записи
        val bw = BufferedWriter(OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_PRIVATE)))

    }
}
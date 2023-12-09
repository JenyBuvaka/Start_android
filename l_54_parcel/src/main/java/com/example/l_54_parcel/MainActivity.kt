package com.example.l_54_parcel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.util.Log

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    lateinit var p:Parcel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeParcel()
        reedParcel()

    }
    fun writeParcel(){
        p = Parcel.obtain()
        val b:Byte = 1
        val i:Int = 2
        val l:Long = 3
        val f:Float = 4.0F
        val d:Double = 5.0
        val s:String = "avbvbdsfg"

        logWriteInfo("before writing")
        p.writeByte(b)
        logWriteInfo("byte")
        p.writeInt(i)
        logWriteInfo("int")
        p.writeLong(l)
        logWriteInfo("long")
        p.writeFloat(f)
        logWriteInfo("float")
        p.writeDouble(d)
        logWriteInfo("dooble")
        p.writeString(s)
        logWriteInfo("string")


    }
    fun logWriteInfo(txt:String){
        Log.d(LOG_TAG,"$txt: dataSize = ${p.dataSize()}")
    }
    fun reedParcel(){
        logReadInfo("before reading");
        p.setDataPosition(0);
        logReadInfo("byte = " + p.readByte());
        logReadInfo("int = " + p.readInt());
        logReadInfo("long = " + p.readLong());
        logReadInfo("float = " + p.readFloat());
        logReadInfo("double = " + p.readDouble());
        logReadInfo("string = " + p.readString());
        println("Если вы хотите глянуть содержимое Parcel можно использовать его метод marshall(), он вернет массив записанных в Parcel байтов"+p.marshall().toString())

    }
    fun logReadInfo(txt:String){
Log.d(LOG_TAG,"$txt : dataPosotion = ${p.dataPosition()}")
    }
}
package com.example.l_55_parcelable

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import android.util.Log

class MyObject(val s:String,  val i: Int):Parcelable {

    companion object {
    const val LOG_TAG = "myLogs"
    @JvmField
    val CREATOR: Parcelable.Creator<MyObject> = object:Parcelable.Creator<MyObject> {
        //Распаковываем обьект их парсэл
        override fun createFromParcel(parcel: Parcel): MyObject {
            Log.d(LOG_TAG,"createFromParsel")
            return MyObject(parcel)
            //CREATOR типа Parcelable.Creator<MyObject> используется для создания экземпляра нашего MyObject и заполнения его данными из Parcel.
        }

        override fun newArray(size: Int): Array<MyObject?> {
            return arrayOfNulls(size)
        }
    }
}


    override fun describeContents(): Int {
    return 0
    }
//Упаковываем обьект в Парсэл
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        Log.d(LOG_TAG,"writeToParcel")
        parcel.writeString(s);
        parcel.writeInt(i);
        //В методе writeToParcel мы получаем на вход Parcel и упаковываем в него наш объект. Т.е., в нашем случае, помещаем туда переменные s и i.  flags не используем
    }
//Конструктор считываюзий данные из Парсэл
    private constructor(parcel: Parcel) : this(
        s= parcel.readString()!!,
        i=parcel.readInt()
    ){
        Log.d(LOG_TAG,"MyObject(Parcel parcel)")
    }//Конструктор MyObject(Parcel parcel) принимает на вход Parcel и заполняет объект данными из него. Этот метод использовался нами чуть ранее в CREATOR.createFromParcel.
}
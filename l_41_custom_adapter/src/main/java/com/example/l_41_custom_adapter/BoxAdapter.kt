package com.example.l_41_custom_adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import java.util.zip.Inflater

class BoxAdapter(private val ctx: Context,private val objects:ArrayList<Product>,): BaseAdapter() {
    private val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //Кол-во элементов
    override fun getCount(): Int {
        return objects.size
    }
//Элемент по позиции
    override fun getItem(position: Int): Any {
        return objects.get(position)
    }
//id  по позиции
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
//Пункт списка
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //Используем созданные но не используемые view
        var view = convertView
    if (view == null){
        view=inflater.inflate(R.layout.item,parent,false)
    }
    val p=getProduct(position)
    // заполняем View в пункте списка данными из товаров: наименование, цена
    // и картинка
    view?.findViewById<TextView>(R.id.tvDescr)?.text=p.name
    view?.findViewById<TextView>(R.id.tvPrice)?.text=("${p.price.toString()}  ")
    view?.findViewById<ImageView>(R.id.ivImage)?.setImageResource(p.image!!)
    val cbBuy = view?.findViewById<CheckBox>(R.id.cbBox)
    //Присваиваем чекбоксу обработчик
    cbBuy?.setOnCheckedChangeListener { buttonView, isChecked ->
        getProduct( buttonView.tag as Int).box = isChecked
    }
    //пишем позицию
    cbBuy?.tag = position

// заполняем данными из товаров: в корзине или нет
    cbBuy?.isChecked = (p.box)
    return view!!
    }
    //Товары по позиции
    private fun getProduct(position: Int): Product {
        return getItem(position)as Product
    }
    //Содержимое корзины
    fun getBox():ArrayList<Product>{
        val box = ArrayList<Product>()
        for (p in objects){
            //Если в корзине
            if (p.box) box.add(p)
        }
return box
    }
}
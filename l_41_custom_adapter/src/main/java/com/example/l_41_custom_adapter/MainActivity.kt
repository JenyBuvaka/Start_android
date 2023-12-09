package com.example.l_41_custom_adapter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var products = ArrayList<Product>()
    private lateinit var boxAdapter: BoxAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // создаем адаптер
        fillData()
        boxAdapter = BoxAdapter(this,products)

        // настраиваем список
        val lvMain = findViewById<ListView>(R.id.lvMain)
        lvMain.adapter = boxAdapter
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            var result = "Товар в корзине"
            for (p in boxAdapter.getBox()){
                if (p.box) result +="\n" + p.name
                Toast.makeText(this,result,Toast.LENGTH_LONG).show()        }
    }
}
    // генерируем данные для адаптера
    private fun fillData(){
        for (i in 1..20){
            products.add(Product("Product" + i,i*1000,R.drawable.ic_launcher_background,false))
        }
    }
    }

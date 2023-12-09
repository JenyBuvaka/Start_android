package com.example.l_13_dnaiclayout3

import android.annotation.SuppressLint
import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toolbar

class MainActivity : AppCompatActivity(),OnSeekBarChangeListener {
    private lateinit var sbWeight:SeekBar
    private lateinit var btn1:Button
    private lateinit var btn2:Button

    private lateinit var lParams1:LayoutParams
    private lateinit var lParams2:LayoutParams
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sbWeight = findViewById(R.id.sbWeight)
        sbWeight.setOnSeekBarChangeListener(this)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        lParams1 = btn1.layoutParams as LayoutParams
        lParams2 = btn2.layoutParams as LayoutParams
    }
//срабатывает все время, пока значение меняется
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
    var leftValue = progress
    var rightValue = seekBar?.max?.minus(progress)
    //настраиваем вес
    lParams1.weight = leftValue.toFloat()
    lParams2.weight = rightValue?.toFloat()!!
    //в текст кнопок пишем значение перменных
    btn1.setText(leftValue.toString())
    btn2.setText(rightValue.toString())
    //переменная leftValue – текущее значение SeekBar, т.е. то что слева от ползунка
    //переменная rightValue – то, что справа от ползунка, т.е. из максимума вычесть текущее значение.
    //
    //Соответственно эти значения и используем как вес.
    // Чем ползунок левее, тем меньше leftValue и больше rightValue,
    // а значит меньше ширина btn1 и больше ширина btn2. И наоборот
}
//срабатывает, когда начинаем тащить ползунок
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
super.onStart()    }
//срабатывает, когда отпускаем ползунок
    override fun onStopTrackingTouch(seekBar: SeekBar?) {
super.onStop()    }

}
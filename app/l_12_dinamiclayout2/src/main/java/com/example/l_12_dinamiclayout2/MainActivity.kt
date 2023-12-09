package com.example.l_12_dinamiclayout2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : Activity(), View.OnClickListener {

    lateinit var llMain: LinearLayout
    lateinit var rgGravity: RadioGroup
    lateinit var etName: EditText
    lateinit var btnCreate: Button
    lateinit var btnClear: Button

    var wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llMain = findViewById(R.id.llMain)
        rgGravity = findViewById(R.id.rgGravity)
        etName = findViewById(R.id.etName)

        btnCreate = findViewById(R.id.btnCreate)
        btnCreate.setOnClickListener(this)

        btnClear = findViewById(R.id.btnClear)
        btnClear.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCreate -> {
                // Обработка нажатия кнопки "Create"
                // Создание LayoutParams c шириной и высотой по содержимому
                val IParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                // переменная для хранения значения выравнивания
                // по умолчанию пусть будет LEFT
                var btnGravity = Gravity.LEFT
                // определяем, какой RadioButton "чекнут" и
                // соответственно заполняем btnGravity

                when (rgGravity.checkedRadioButtonId) {
                    R.id.rbLeft -> {btnGravity=Gravity.LEFT}
                    R.id.rbCenter->{btnGravity=Gravity.CENTER}
                    R.id.rbRight->{btnGravity=Gravity.RIGHT}

                }
                // переносим полученное значение выравнивания в LayoutParams
                IParams.gravity=btnGravity
                // создаем Button, пишем текст и добавляем в LinearLayout
                val btnNew = Button(this)
                btnNew.setText(etName.text.toString())
                llMain.addView(btnNew,IParams)
            }
            R.id.btnClear->{
                llMain.removeAllViews()
                Toast.makeText(this,"Удалено",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

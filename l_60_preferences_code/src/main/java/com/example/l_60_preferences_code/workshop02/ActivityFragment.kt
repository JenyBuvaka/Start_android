package com.example.l_60_preferences_code.workshop02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.android.fundamentals.workshop02.WS02RootFragment
import com.android.fundamentals.workshop02.WS02SecondFragment
import com.example.l_60_preferences_code.R

class ActivityFragment(): AppCompatActivity(),WS02RootFragment.TransactionsFragmentClicks {
    var count = 0
    private val rootFragment =
        WS02RootFragment().apply { setClickListener(this@ActivityFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws02_ws03)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.persistent_container,rootFragment).commit()
        }
    }
    override fun addRedFragment() {
        count++
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragments_container,WS02SecondFragment.newInstance(count,R.color.red)).commit()

        }
    }

    override fun addBlueFragment() {
        count++
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragments_container,WS02SecondFragment.newInstance(count,R.color.blue)).commit()
        }
    }

    override fun removeLast() {
        count--
        if (supportFragmentManager.fragments.size>1){
            val lastFragment = supportFragmentManager.fragments.last()
            supportFragmentManager.beginTransaction().remove(lastFragment).commit()
        }
        }

    override fun replaceFragment() {
        count =1
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragments_container,WS02SecondFragment.newInstance(count,R.color.green))
                .commit()}
    }
}
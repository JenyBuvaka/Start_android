package com.android.fundamentals.workshop03

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.l_60_preferences_code.R

class WS03SecondFragment : Fragment() {

    private var count = 0
    private var tvValue: TextView? = null
    private var backgroundColor: BackgroundColor = BackgroundColor.WHITE
    private var wS03SecondFragment:WS03SecondFragment?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_second_ws_03, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvValue = view.findViewById<TextView>(R.id.tv_value).apply {
            text = savedInstanceState?.getInt("count").toString() ?: "$count"
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count",count)
    }
    fun increaseValue() {
        count++
        tvValue?.text = "$count"
    }

    fun changeBackground() {
        backgroundColor = backgroundColor.change()
        tvValue?.apply {
            background = ContextCompat.getDrawable(context, backgroundColor.resId)
        }
    }

    private fun BackgroundColor.change() = when (this) {
        BackgroundColor.PURPLE -> BackgroundColor.TEAL
        BackgroundColor.TEAL -> BackgroundColor.WHITE
        BackgroundColor.WHITE -> BackgroundColor.PURPLE
    }

    private enum class BackgroundColor(val resId: Int) {
        PURPLE(R.color.purple_200),
        TEAL(R.color.teal_200),
        WHITE(R.color.white)
    }
}
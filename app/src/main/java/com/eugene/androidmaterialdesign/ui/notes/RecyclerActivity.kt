package com.eugene.androidmaterialdesign.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import com.eugene.androidmaterialdesign.R
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter
    private val NAME_SHARED_PREFERENCE = "LOGIN"
    private val APP_THEME = "APP_THEME"
    private val MARS_THEME = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val data = mutableListOf(
            Pair(Data("New Note", ""), false))
        data.add(0, Pair(Data("Header", ""), false))

        adapter = RecyclerAdapter(data)
        recyclerView.adapter = adapter
        recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
        }
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(recyclerView)
    }

    private fun getAppTheme() {
        val sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE)
        if (sharedPreferences != null) {
            val codeStyle = sharedPreferences.getInt(APP_THEME, 0)
            if (codeStyle == MARS_THEME) {
                setTheme(R.style.MarsTheme)
            } else {
                setTheme(R.style.SpaceTheme)
            }
        }
    }
}
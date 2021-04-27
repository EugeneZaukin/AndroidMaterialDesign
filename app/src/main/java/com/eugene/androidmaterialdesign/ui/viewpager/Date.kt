package com.eugene.androidmaterialdesign.ui.viewpager

import java.util.*

class Date {
    private val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
}
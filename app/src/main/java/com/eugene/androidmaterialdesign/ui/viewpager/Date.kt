package com.eugene.androidmaterialdesign.ui.viewpager

import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT_DATE = "yyyy-MM-dd"

class Date {
    private val simpleDateFormat = SimpleDateFormat(FORMAT_DATE, Locale.getDefault())
    private val calendarToday = Calendar.getInstance()
    private val calendarYesterday = Calendar.getInstance().apply {
        add(Calendar.DATE, -1)
    }
    private val calendarTheDayBeforeYesterday = Calendar.getInstance().apply {
        add(Calendar.DATE, -2)
    }

    val today:String get() = simpleDateFormat.format(calendarToday.time)
    val yesterday:String get() = simpleDateFormat.format(calendarYesterday.time)
    val theDayBeforeYesterday:String get() = simpleDateFormat.format(calendarTheDayBeforeYesterday.time)
}
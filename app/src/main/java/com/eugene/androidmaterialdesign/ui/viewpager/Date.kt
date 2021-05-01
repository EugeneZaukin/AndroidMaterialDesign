package com.eugene.androidmaterialdesign.ui.viewpager

import java.text.SimpleDateFormat
import java.util.*

class Date {
    private val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val calendarToday = Calendar.getInstance()
    private val calendarYesterday = Calendar.getInstance()
    private val calendarTheDayBeforeYesterday = Calendar.getInstance()

    private val yesterdayDate = (calendarYesterday.add(Calendar.DATE, -1))
    private val theDayBeforeYesterdayDate = calendarTheDayBeforeYesterday.add(Calendar.DATE, -2)

    val today:String get() = formatDate.format(calendarToday.time)
    val yesterday:String get() = formatDate.format(calendarYesterday.time)
    val theDayBeforeYesterday:String get() = formatDate.format(calendarTheDayBeforeYesterday.time)
}
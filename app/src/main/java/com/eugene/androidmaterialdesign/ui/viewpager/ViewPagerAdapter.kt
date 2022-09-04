package com.eugene.androidmaterialdesign.ui.viewpager

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    date: Date
): FragmentStateAdapter(fragmentActivity) {
    private var list: List<String> = listOf(date.theDayBeforeYesterday, date.yesterday, date.today)

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        return DayFragment.newInstance(list[position])
    }
}
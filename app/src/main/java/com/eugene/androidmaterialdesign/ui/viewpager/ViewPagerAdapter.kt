package com.eugene.androidmaterialdesign.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val TODAY_IMAGE = 2
private const val YESTERDAY_IMAGE = 1
private const val THE_DAY_BEFORE_YESTERDAY_IMAGE = 0

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(DayFragment(), DayFragment(), DayFragment());

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> fragments[THE_DAY_BEFORE_YESTERDAY_IMAGE]
            1 -> fragments[YESTERDAY_IMAGE]
            2 -> fragments[TODAY_IMAGE]
            else -> fragments[TODAY_IMAGE]
        }
    }


}
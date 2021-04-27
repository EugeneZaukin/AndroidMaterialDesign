package com.eugene.androidmaterialdesign.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val TODAY_PLANET = 0
private const val YESTERDAY_PLANET = 1

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(TodayFragment(), YesterdayFragment())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> fragments[YESTERDAY_PLANET]
            1 -> fragments[TODAY_PLANET]
            else -> fragments[TODAY_PLANET]
        }
    }
}
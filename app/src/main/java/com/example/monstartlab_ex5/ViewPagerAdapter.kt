package com.example.monstartlab_ex5

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, behavior: Int, private var list: ArrayList<String>) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        var str: String = list[position]
        val calendarFragment: CalendarFragment = CalendarFragment()
        val bundle: Bundle = Bundle()
        bundle.putString("list",str)
        calendarFragment.arguments = bundle
        return calendarFragment
    }

    override fun getCount(): Int {
        if (list != null) {
            return list.size
        }
        return 0
    }

}
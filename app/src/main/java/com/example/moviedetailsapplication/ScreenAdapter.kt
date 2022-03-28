package com.example.moviedetailsapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ScreenAdapter(private val titles: Array<String>, fm: FragmentManager, behaviour: Int) : FragmentStatePagerAdapter(fm,behaviour) {
    override fun getCount(): Int =2

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->PopularFragment()
            1->CurrentYearFragment()
            else -> error("Position Not Defined")
        }
    }

    override fun getPageTitle(position: Int)= titles[position]
}
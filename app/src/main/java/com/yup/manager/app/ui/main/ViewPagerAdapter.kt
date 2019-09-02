package com.yup.manager.app.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

//created by Ilmir Shagabiev

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStateAdapter(fragmentManager) {

    private var fragmentList: MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getItemCount(): Int = fragmentList.size

    fun addFragment(fragment: Fragment) = fragmentList.add(fragment)

}

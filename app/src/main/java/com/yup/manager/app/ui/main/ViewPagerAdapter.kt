package com.yup.manager.app.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yup.manager.R
import kotlinx.android.synthetic.main.activity_main.view.*

//created by Ilmir Shagabiev

class ViewPagerAdapter(private val fragmentManager: FragmentManager) : FragmentStateAdapter(fragmentManager) {

    private var fragmentList: MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getItemCount(): Int = fragmentList.size

    fun addFragment(fragment: Fragment) = fragmentList.add(fragment)

    fun showOrdersList(){
        fragmentManager.findFragmentById(R.id.view_pager_main)?.let {
            fragmentManager.beginTransaction()
                .hide(it)
                .show(fragmentList.last())
                .commitAllowingStateLoss()
        }
    }

}

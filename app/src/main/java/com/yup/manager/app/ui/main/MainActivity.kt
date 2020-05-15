package com.yup.manager.app.ui.main

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.yup.manager.R
import com.yup.manager.app.ui.main.orders.OrdersFragment
import com.yup.manager.app.ui.main.profile.ProfileFragment
import com.yup.manager.data.utils.getCurrentDay
import com.yup.manager.data.utils.getCurrentMonth
import com.yup.manager.data.utils.getCurrentYear
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainView {

    private val DATE_FROM_ORDER = 1

    var adapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter?.addFragment(ProfileFragment.newInstance())
        adapter?.addFragment(OrdersFragment.newInstance())

        view_pager_main.adapter = adapter
        view_pager_main.currentItem = 1
    }

    override fun setNewCurrent(position: Int) {
        view_pager_main.currentItem = position
    }

    override fun onResume() {
        super.onResume()
        view_pager_main.adapter = adapter
        view_pager_main.currentItem = 1
    }

}

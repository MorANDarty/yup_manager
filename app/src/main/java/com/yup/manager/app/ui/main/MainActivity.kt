package com.yup.manager.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yup.manager.R
import com.yup.manager.app.ui.main.orders.OrdersFragment
import com.yup.manager.app.ui.main.orders.orderInfo.OrderInfoFragment
import com.yup.manager.app.ui.main.profile.ProfileFragment
import com.yup.manager.domain.utils.ORDER_INFO_FRAGMENT
import com.yup.manager.domain.utils.ORDER_LIST_FRAGMENT
import com.yup.manager.domain.utils.PROFILE_FRAGMENT

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showOrdersList()
    }

    override fun showOrderInfo(bundle: Bundle) {
        val fragment = OrderInfoFragment()
        fragment.arguments = bundle

        supportFragmentManager.findFragmentById(R.id.container_main)?.let {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                .hide(it)
                .add(R.id.container_main, fragment)
                .addToBackStack(ORDER_INFO_FRAGMENT)
                .commitAllowingStateLoss()
        }
    }

    override fun showOrdersList() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .add(R.id.container_main, OrdersFragment())
            .addToBackStack(ORDER_LIST_FRAGMENT)
            .commitAllowingStateLoss()
    }

    override fun showProfile() {
        supportFragmentManager.findFragmentById(R.id.container_main)?.let {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                .hide(it)
                .add(R.id.container_main, ProfileFragment())
                .addToBackStack(PROFILE_FRAGMENT)
                .commitAllowingStateLoss()
        }
    }

    override fun onBackFromOrderInfo() {
        supportFragmentManager.popBackStack()

        val fragment = supportFragmentManager.findFragmentById(R.id.container_main)
        if (fragment is OrdersFragment) {
            fragment.updateOrders()
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentById(R.id.container_main) is OrdersFragment){
            System.exit(0)
        }else{
            super.onBackPressed()
        }
    }

}

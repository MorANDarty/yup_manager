package com.yup.manager.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yup.manager.R
import com.yup.manager.app.ui.main.orders.OrdersFragment
import com.yup.manager.app.ui.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*rv_orders_main.layoutManager = LinearLayoutManager(this)
        rv_orders_main.adapter = OrdersListAdapter(getSomeOrders().toMutableList())
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder.itemView.tag == "unchecked") {
                    (rv_orders_main.adapter as OrdersListAdapter).addItem(OrderSample("14:20","New customer",
                        "New activity", "","checked"), viewHolder.adapterPosition)
                    //Todo show dialog
                } else {
                    (rv_orders_main.adapter as OrdersListAdapter).removeAt(viewHolder.adapterPosition)
                    //Todo show dialog
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv_orders_main)*/

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProfileFragment.newInstance())
        adapter.addFragment(OrdersFragment.newInstance())

        view_pager_main.adapter = adapter
    }

}

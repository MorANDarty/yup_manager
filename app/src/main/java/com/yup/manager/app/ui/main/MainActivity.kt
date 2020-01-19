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
        view_pager_main.currentItem = 1
    }

    override fun setNewCurrent(position: Int) {
        view_pager_main.currentItem = position
    }

    override fun onCreateDialog(id: Int): Dialog {
        when(id){
            DATE_FROM_ORDER->{
                return DatePickerDialog(this, dateCallback, getCurrentYear(), getCurrentMonth(), getCurrentDay())
            }
        }
        return super.onCreateDialog(id)
    }

    private var dateCallback = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
            Timber.d("on date from call back date set")
            val fragment = supportFragmentManager.findFragmentById(R.id.view_pager_main)
            if (fragment is OrdersFragment) {
                fragment.setNewDate(year, month, day)
                Timber.d("day = $day, month = $month, year = $year")
            }
        }
    }

}

package com.yup.manager.app.ui.main.orders

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yup.manager.R
import com.yup.manager.domain.utils.getSomeOrders
import kotlinx.android.synthetic.main.fragment_orders.*
import android.view.MotionEvent
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.yup.manager.app.ManagerApplication
import com.yup.manager.app.ui.qrScanning.ScanningActivity
import com.yup.manager.app.ui.ViewModelFactory
import com.yup.manager.app.ui.main.MainActivity
import com.yup.manager.app.ui.main.MainView
import com.yup.manager.data.utils.getCurrentDay
import com.yup.manager.data.utils.getCurrentMonth
import com.yup.manager.data.utils.getCurrentYear
import com.yup.manager.data.utils.getDayOfWeekString
import com.yup.manager.domain.entities.order.OrderSample
import com.yup.manager.domain.entities.order.accessory.Order
import kotlinx.android.synthetic.main.fragment_orders.view.*
import java.util.*
import javax.inject.Inject

class OrdersFragment : Fragment(), OrdersCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var orderViewModel: OrderViewModel

    companion object {
        private const val REQUEST_CODE_FROM = 1

        @JvmStatic
        fun newInstance() = OrdersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_orders, container, false)
        v.btn_scan.setOnClickListener {
            val intent = Intent(activity as MainActivity, ScanningActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_FROM)
        }

        v.img_menu_ic.setOnClickListener {
            (activity as MainView).setNewCurrent(0)
        }
        v.window_info.alpha = 0.0f
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as ManagerApplication).getAppComponent()?.inject(this)
        orderViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(OrderViewModel::class.java)
        observeLoadingData()
        orderViewModel.getOrders()
        observeOrderListData()
    }

    private fun observeLoadingData() {
        orderViewModel.showLoadingLiveData.observe(this, Observer {
            showLoading(it)
        })
    }

    private fun observeOrderListData() {
        orderViewModel.orderListLiveData.removeObservers(viewLifecycleOwner)
        orderViewModel.orderListLiveData.observe(this, Observer {
            if(it.data!=null){
                rv_orders.layoutManager = LinearLayoutManager(context)
                rv_orders.adapter = OrdersListAdapter(it.data as MutableList<Order>, this)
            }
            else if(it.error!=null){
                Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showLoading(state:Boolean?){
        if(state==true){
            pb_order_list.visibility = View.VISIBLE
        }
        if(state == false){
            pb_order_list.visibility = View.INVISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_FROM -> {
                    val imgUrl = data?.getStringExtra("img_url")
                    val time = data?.getStringExtra("time")
                    val name = data?.getStringExtra("name")
                    val details = data?.getStringExtra("details")
                    //TODO
                    Glide.with(this).load(imgUrl).into(img_scanning)
                    tv_time_scanning.text = time
                    tv_name_scanning.text = name
                    tv_event_details_scanning.text = details
                    window_info.animate().alpha(1.0f).setDuration(300)
                    window_info.setOnClickListener {
                        window_info.animate().alpha(0.0f).setDuration(300)
                    }
                }
            }
        }
    }

    override fun onCallback(order: Order) {

    }

}

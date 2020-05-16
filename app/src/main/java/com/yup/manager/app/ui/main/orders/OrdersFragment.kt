package com.yup.manager.app.ui.main.orders

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yup.manager.R
import com.yup.manager.app.ManagerApplication
import com.yup.manager.app.ui.ViewModelFactory
import com.yup.manager.app.ui.main.MainActivity
import com.yup.manager.app.ui.main.MainView
import com.yup.manager.app.ui.qrScanning.ScanningActivity
import com.yup.manager.domain.entities.order.accessory.Order
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.fragment_orders.view.*
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
            (activity as MainView).showProfile()
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

    fun updateOrders() {
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
            if (it.data != null) {
                rv_orders.layoutManager = LinearLayoutManager(context)
                rv_orders.adapter = OrdersListAdapter(it.data as MutableList<Order>, this)
            } else if (it.error != null) {
                Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showLoading(state: Boolean?) {
        if (state == true) {
            pb_order_list.visibility = View.VISIBLE
        }
        if (state == false) {
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
        val bundle = Bundle()
        bundle.putString("order_id", order.id)
        bundle.putString("order_name", order.name)
        bundle.putString("user_name_and_surname", order.userName + " " + order.userSurname)
        bundle.putString("user_phone", order.userPhone)
        bundle.putString("user_avatar", order.userAvatar)
        bundle.putString("order_time", order.time)
        bundle.putString("order_cost", order.cost)
        bundle.putString("order_participants", order.maxParticipants.toString() + " человек")
        bundle.putString("order_comment", order.comment)
        bundle.putString("order_state", order.state)
        (activity as MainView).showOrderInfo(bundle)
    }

}

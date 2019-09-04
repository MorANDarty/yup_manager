package com.yup.manager.app.ui.main.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yup.manager.R
import com.yup.manager.domain.entities.order.OrderSample
import com.yup.manager.domain.utils.getSomeOrders
import kotlinx.android.synthetic.main.fragment_orders.*
import android.view.MotionEvent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yup.manager.app.ManagerApplication
import com.yup.manager.app.ui.ViewModelFactory
import javax.inject.Inject

class OrdersFragment : Fragment() {

    val orderList = getSomeOrders()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(OrderViewModel::class.java)
        observeLoadingData()
        observeOrderListData()
        (activity?.application as ManagerApplication).getAppComponent()?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderViewModel.getMockOrders()
        rv_orders.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_orders.adapter = OrdersListAdapter(getSomeOrders().toMutableList())
        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder.itemView.tag == "unchecked") {
                    //Todo show dialog and add item
                } else {
                    (rv_orders.adapter as OrdersListAdapter).removeAt(viewHolder.adapterPosition)
                    //Todo show dialog
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv_orders)

        rv_orders.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.action
                when (action) {
                    MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }
        })
    }

    private fun observeLoadingData() {
        orderViewModel.orderListLiveData.observe(this, Observer {
            if(it.data!=null){
                it.data.forEach {
                    orderList.add(it)
                }
                rv_orders.adapter = OrdersListAdapter(orderList)
            }

        })
    }

    private fun observeOrderListData() {
        orderViewModel.showLoadingLiveData.observe(this, Observer {
            showLoading(it)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = OrdersFragment()
    }

    fun showLoading(state:Boolean?){
        if(state==true){
            pb_order_list.visibility = View.VISIBLE
        }
        if(state == false){
            pb_order_list.visibility = View.INVISIBLE
        }
    }
}

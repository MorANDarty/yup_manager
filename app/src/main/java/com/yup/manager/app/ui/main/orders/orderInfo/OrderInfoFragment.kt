package com.yup.manager.app.ui.main.orders.orderInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yup.manager.R
import com.yup.manager.app.ManagerApplication
import com.yup.manager.app.ui.ViewModelFactory
import com.yup.manager.app.ui.base.BaseFragment
import com.yup.manager.app.ui.main.MainView
import com.yup.manager.data.utils.getDateAndTimeString
import com.yup.manager.domain.utils.STATE_APPROVED
import com.yup.manager.domain.utils.STATE_COMPLETED
import com.yup.manager.domain.utils.STATE_EXPECTATION
import com.yup.manager.domain.utils.STATE_REJECTED
import kotlinx.android.synthetic.main.fragment_order_info.*
import javax.inject.Inject

class OrderInfoFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: OrderInfoViewModel
    private var orderId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ManagerApplication.get().getAppComponent()?.inject(this)
        initOrderData()
    }

    fun initOrderData() {
        val imgUrl = arguments?.getString("user_avatar")
        Glide.with(this).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .apply(RequestOptions().circleCrop()).into(img_avatar_user)

        tv_name_and_surname_user.text = arguments?.getString("user_name_and_surname")
        tv_phone_user.text = arguments?.getString("user_phone")
        tv_order_name.text = arguments?.getString("order_name")
        tv_order_time.text = arguments?.getString("order_time")?.let { getDateAndTimeString(it) }
        tv_order_cost.text = arguments?.getString("order_cost")
        tv_order_participants.text = arguments?.getString("order_participants")
        tv_comment_order.text = arguments?.getString("order_comment")

        orderId = arguments?.getString("order_id")

        arguments?.getString("order_state")?.let {state->
            initButtons(state)
        }

        btn_back_order_info.setOnClickListener {
            (activity as MainView).onBackFromOrderInfo()
        }
    }

    private fun initButtons(state: String) {
        when (state) {
            STATE_EXPECTATION -> {
                tv_order_state.text = "Статус - в ожидании"
                btn_approve_order.setOnClickListener {
                    orderId?.let { id ->
                        startPb()
                        viewModel.approveOrder(id)
                        observeApproving()
                    }
                }

                btn_reject_order.setOnClickListener {
                    orderId?.let{ id->
                        startPb()
                        viewModel.rejectOrder(id)
                        observeRejecting()
                    }
                }
            }

            STATE_COMPLETED -> {
                tv_order_state.text = "Статус - закончено"
                btn_reject_order.visibility = View.INVISIBLE
                btn_approve_order.visibility = View.INVISIBLE
            }

            STATE_APPROVED -> {
                tv_order_state.text = "Статус - принято"
                btn_approve_order.visibility = View.INVISIBLE
                btn_reject_order.setOnClickListener {
                    orderId?.let{ id->
                        startPb()
                        viewModel.rejectOrder(id)
                        observeRejecting()
                    }
                }
            }

            STATE_REJECTED -> {
                tv_order_state.text = "Статус - отклонено"
                btn_reject_order.visibility = View.INVISIBLE
                btn_approve_order.visibility = View.INVISIBLE
            }
        }
    }

    private fun observeApproving() {
        viewModel.approveLiveData.removeObservers(viewLifecycleOwner)
        viewModel.approveLiveData.observe(viewLifecycleOwner, Observer {
            finishPb()
            if (it.data != null) {
                tv_order_state.text = "Статус - принято"
                toast("Подтвердили заявку!")
                btn_approve_order.visibility = View.INVISIBLE
                btn_reject_order.visibility =  View.VISIBLE
            }
            if (it.error != null) {
                it.error.message?.let { msg -> toast(msg) }
            }
        })
    }

    private fun observeRejecting() {
        viewModel.rejectLiveData.removeObservers(viewLifecycleOwner)
        viewModel.rejectLiveData.observe(viewLifecycleOwner, Observer {
            finishPb()
            if (it.data != null) {
                toast("Отклонили заявку :(")
                tv_order_state.text = "Статус - отклонено"
                btn_reject_order.visibility = View.INVISIBLE
                btn_approve_order.visibility = View.INVISIBLE
            }
            if (it.error != null){
                it.error.message?.let { msg -> toast(msg) }
            }
        })
    }

    private fun finishPb(){
        pb_order_info.visibility = View.INVISIBLE
    }

    private fun startPb(){
        pb_order_info.visibility = View.VISIBLE
    }

}

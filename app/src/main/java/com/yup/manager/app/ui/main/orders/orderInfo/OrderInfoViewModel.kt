package com.yup.manager.app.ui.main.orders.orderInfo

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.data.SessionManager
import com.yup.manager.data.rest.RestApiService
import com.yup.manager.data.utils.Response
import com.yup.manager.domain.entities.order.RespOrder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class OrderInfoViewModel @Inject constructor(
    private val sm: SessionManager,
    private val api: RestApiService
) : BaseViewModel() {

    val approveLiveData = MutableLiveData<Response<RespOrder>>()
    val rejectLiveData = MutableLiveData<Response<RespOrder>>()

    fun rejectOrder(orderId: String) {
        sm.getToken()?.let {
            api.rejectOrder(it, orderId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        rejectLiveData.value = Response(it, null)
                        rejectLiveData.value = Response(null, null)
                    },
                    {
                        rejectLiveData.value = Response(null, it)
                        rejectLiveData.value = Response(null, null)
                    }
                )
        }?.let { disposables.add(it) }
    }

    fun approveOrder(orderId: String) {
        sm.getToken()?.let {
            api.approveOrder(it, orderId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        approveLiveData.value = Response(it, null)
                        approveLiveData.value = Response(null, null)
                    },
                    {
                        approveLiveData.value = Response(null, it)
                        approveLiveData.value = Response(null, null)
                    }
                )
        }?.let { disposables.add(it) }
    }
}
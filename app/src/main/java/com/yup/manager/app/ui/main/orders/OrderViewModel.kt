package com.yup.manager.app.ui.main.orders

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.interactors.OrdersInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.data.utils.Response
import com.yup.manager.domain.entities.order.OrderSample
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


//created by Ilmir Shagabiev

class OrderViewModel @Inject constructor(
    private val ordersInteractor: OrdersInteractor
) : BaseViewModel(){

    var orderListLiveData = MutableLiveData<Response<List<OrderSample>>>()

    fun getMockOrders(){
        showLoadingLiveData.value = true
        disposables.add(
            ordersInteractor.getMockOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showLoadingLiveData.value = false }
                .subscribe({
                    orderListLiveData.value = Response.success(it)
                },{
                    orderListLiveData.value = Response.error(it)
                    it.printStackTrace()
                })
        )
    }
}

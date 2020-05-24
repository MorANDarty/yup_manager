package com.yup.manager.app.ui.main.orders

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.interactors.OrdersInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.app.utils.simpleLog
import com.yup.manager.data.SessionManager
import com.yup.manager.data.utils.Response
import com.yup.manager.domain.entities.order.accessory.Order
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject


//created by Ilmir Shagabiev

class OrderViewModel @Inject constructor(
    private val ordersInteractor: OrdersInteractor,
    private val sm: SessionManager
) : BaseViewModel() {

    var orderListLiveData = MutableLiveData<Response<List<Order>>>()
    var orderDeleteLiveData = MutableLiveData<Response<Boolean>>()
    var message: String? = null

    fun getMockOrders() {
        showLoadingLiveData.value = true
        disposables.add(
            ordersInteractor.getMockOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showLoadingLiveData.value = false }
                .subscribe({
                    /* orderListLiveData.value = Response.success(it)*/
                }, {
                    orderListLiveData.value = Response.error(it)
                    it.printStackTrace()
                })
        )
    }

    fun deleteOrder(id: Int) {
        showLoadingLiveData.value = true
        disposables.add(
            ordersInteractor.deleteOrder(id)
                .doFinally { showLoadingLiveData.value = false }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it == "Nice") {
                        orderDeleteLiveData.value = Response.success(true)
                    }
                }, {
                    orderDeleteLiveData.value = Response.error(it)
                    if (it is HttpException) {
                        when (it.code()) {
                            422 -> message = "Что то пошло не так"
                            500 -> {

                            }
                        }
                    } else {
                        message = "Ошибка сети"
                    }
                })
        )
    }

    fun getOrders() {
        simpleLog("START_GET_ORDERS")
        showLoadingLiveData.value = true
        sm.getToken()?.let {
            ordersInteractor
                .getOrders(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally { showLoadingLiveData.value = false }
                .subscribe(
                    {
                        simpleLog(it.toString())
                        val allOrders = arrayListOf<Order>()
                        it.blocks.forEach { block ->
                            block.orders.forEach { o ->
                                val order = o
                                order.name = block.name
                                allOrders.add(order)
                            }
                        }
                        orderListLiveData.value = Response(allOrders, null)
                        orderListLiveData.value = Response(null, null)
                    },
                    {
                        simpleLog(it.toString())
                        orderListLiveData.value = Response(null, it)
                        orderListLiveData.value = Response(null, null)
                    }
                )
        }?.let {
            disposables.add(
                it
            )
        }
    }
}

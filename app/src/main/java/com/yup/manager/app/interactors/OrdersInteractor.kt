package com.yup.manager.app.interactors

import com.yup.manager.domain.entities.order.OrderSample
import com.yup.manager.domain.entities.order.RespOrder
import com.yup.manager.domain.entities.order.RespOrdersList
import com.yup.manager.domain.entities.order.accessory.Order
import com.yup.manager.domain.entities.order.accessory.UpdateOrderResp
import com.yup.manager.domain.repositories.IOrderRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


//created by Ilmir Shagabiev

class OrdersInteractor @Inject constructor(
    private val orderRepo: IOrderRepository
) {

    fun getMockOrders(): Single<List<OrderSample>> = orderRepo.getDefault().subscribeOn(Schedulers.io())

    fun deleteOrder(id: Int): Single<String> = "Nice".toSingle().subscribeOn(Schedulers.io())

    fun scanQr(info:String?) = orderRepo.scanQr(info).subscribeOn(Schedulers.io())

     fun approveOrder(token: String, orderId: String): Single<UpdateOrderResp> =
        orderRepo.approveOrder(token, orderId)

     fun cancelOrder(token: String, orderId: String): Single<UpdateOrderResp> =
        orderRepo.cancelOrder(token, orderId)

     fun completeOrder(token: String, orderId: String): Single<RespOrder> = orderRepo.completeOrder(token, orderId)

     fun getOrders(token: String): Single<RespOrdersList> = orderRepo.getOrders(token)
}

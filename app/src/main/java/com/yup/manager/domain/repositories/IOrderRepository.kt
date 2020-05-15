package com.yup.manager.domain.repositories

import com.yup.manager.domain.entities.order.OrderSample
import com.yup.manager.domain.entities.order.RespOrder
import com.yup.manager.domain.entities.order.RespOrdersList
import com.yup.manager.domain.entities.order.RespScanning
import io.reactivex.Single

//created by Ilmir Shagabiev

interface IOrderRepository {

    fun getDefault(): Single<List<OrderSample>>
    fun scanQr(info: String?): Single<RespScanning>

    fun getOrders(token:String): Single<RespOrdersList>
    fun cancelOrder(token:String, orderId:String): Single<RespOrder>
    fun approveOrder(token:String, orderId:String): Single<RespOrder>
    fun completeOrder(token:String, orderId:String): Single<RespOrder>

}

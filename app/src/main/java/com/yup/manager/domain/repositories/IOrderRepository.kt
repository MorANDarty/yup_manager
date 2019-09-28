package com.yup.manager.domain.repositories

import com.yup.manager.domain.entities.order.OrderSample
import com.yup.manager.domain.entities.order.RespScanning
import io.reactivex.Single

//created by Ilmir Shagabiev

interface IOrderRepository {

    fun getDefault(): Single<List<OrderSample>>
    fun scanQr(info:String?):Single<RespScanning>
}
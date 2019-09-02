package com.yup.manager.domain.repositories

import com.yup.manager.domain.entities.order.OrderSample
import io.reactivex.Single

//created by Ilmir Shagabiev

interface IOrderRepository {

    fun getDefault(): Single<List<OrderSample>>
}
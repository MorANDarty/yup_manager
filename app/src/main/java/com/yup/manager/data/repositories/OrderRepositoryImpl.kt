package com.yup.manager.data.repositories

import com.yup.manager.domain.entities.order.OrderSample
import com.yup.manager.domain.repositories.IOrderRepository
import com.yup.manager.domain.utils.getSomeOrders
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

//created by Ilmir Shagabiev

class OrderRepositoryImpl @Inject constructor():IOrderRepository {

    override fun getDefault(): Single<List<OrderSample>> = getSomeOrders().toSingle()
}
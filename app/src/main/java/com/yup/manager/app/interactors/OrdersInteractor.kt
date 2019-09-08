package com.yup.manager.app.interactors

import com.yup.manager.domain.entities.order.OrderSample
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
    fun deleteOrder(id: Int): Single<String> = "Nice".toSingle()

}

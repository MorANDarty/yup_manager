package com.yup.manager.data.repositories

import com.yup.manager.data.rest.RestApiService
import com.yup.manager.domain.entities.order.OrderSample
import com.yup.manager.domain.entities.order.RespOrder
import com.yup.manager.domain.entities.order.RespOrdersList
import com.yup.manager.domain.entities.order.RespScanning
import com.yup.manager.domain.repositories.IOrderRepository
import com.yup.manager.domain.utils.getSomeOrders
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

//created by Ilmir Shagabiev

class OrderRepositoryImpl @Inject constructor(private val restApiService: RestApiService) :
    IOrderRepository {

    override fun getDefault(): Single<List<OrderSample>> = getSomeOrders().toSingle()
    override fun scanQr(info: String?): Single<RespScanning> = RespScanning(
        "message", "Виталий Цаль", "13:20",
        "https://svirtus.cdnvideo.ru/o1C2I9RaGfz901f0LSoJ7MZXB6w=/0x0:328x326/800x0/filters:quality(100)/https://hb.bizmrg.com/cybersportru-media/18/1837e4f1f193853c670504eeb82e3fff.jpg?m=2adb40fee88f58e4d9e2104f5ebd2f09",
        "Аренда Эрика lfknvfdklvnfvkdn vdkflvnkoer nvdlknvmd "
    ).toSingle()


    override fun approveOrder(token: String, orderId: String): Single<RespOrder> =
        restApiService.approveOrder(token, orderId)

    override fun cancelOrder(token: String, orderId: String): Single<RespOrder> =
        restApiService.rejectOrder(token, orderId)

    override fun completeOrder(token: String, orderId: String): Single<RespOrder> = restApiService.completeOrder(token, orderId)

    override fun getOrders(token: String): Single<RespOrdersList> = restApiService.getOrders(token)

}
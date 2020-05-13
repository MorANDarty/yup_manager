package com.yup.manager.data.dataSources.rest

import com.yup.manager.domain.entities.order.RespOrder
import com.yup.manager.domain.entities.order.RespOrdersList
import com.yup.manager.domain.entities.user.LoginReq
import com.yup.manager.domain.entities.user.LoginResp
import io.reactivex.Single
import retrofit2.http.*


interface RestApi {

    @POST("/company/manager/login")
    fun signIn(@Body signInForm: LoginReq): Single<LoginResp>

    @GET("/company/manager/orders")
    fun getOrders(@Header("Authorization") token: String): Single<RespOrdersList>

    @GET("/company/manager/orders/{id}/")
    fun rejectOrder(
        @Header("Authorization") token: String,
        @Path("id") orderId: String
    ):Single<RespOrder>

}
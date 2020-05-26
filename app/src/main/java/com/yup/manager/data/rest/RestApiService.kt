package com.yup.manager.data.rest

import com.yup.manager.domain.entities.order.ReqUpdateBody
import com.yup.manager.domain.entities.order.RespOrder
import com.yup.manager.domain.entities.order.RespOrdersList
import com.yup.manager.domain.entities.order.accessory.UpdateOrderResp
import com.yup.manager.domain.entities.user.LoginReq
import com.yup.manager.domain.entities.user.LoginResp
import com.yup.manager.domain.entities.user.UserInfoResp
import io.reactivex.Single
import retrofit2.http.*

//created by Ilmir Shagabiev

interface RestApiService {

    @POST("/manager/login")
    fun signIn(@Body signInForm: LoginReq): Single<LoginResp>

    @GET("/manager/order")
    fun getOrders(@Header("Authorization") token: String): Single<RespOrdersList>

    @GET("/manager/orders/{id}/reject")
    fun rejectOrder(
        @Header("Authorization") token: String,
        @Path("id") orderId: String
    ): Single<RespOrder>

    @GET("/manager/orders/{id}/approve")
    fun approveOrder(
        @Header("Authorization") token: String,
        @Path("id") orderId: String
    ): Single<UpdateOrderResp>

    @GET("/manager/orders/{id}/complete")
    fun completeOrder(
        @Header("Authorization") token: String,
        @Path("id") orderId: String
    ): Single<RespOrder>

    @POST("/manager/order/update")
    fun updateOrderState(
        @Header("Authorization") token: String,
        @Body updateBody: ReqUpdateBody
    ): Single<UpdateOrderResp>

    @GET("/me")
    fun getMyInfo(
        @Header("Authorization") token: String
    ): Single<UserInfoResp>
}
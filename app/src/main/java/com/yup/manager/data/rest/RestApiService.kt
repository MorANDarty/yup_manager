package com.yup.manager.data.rest

import com.yup.manager.domain.entities.order.RespScanning
import io.reactivex.Single
import retrofit2.http.GET

//created by Ilmir Shagabiev

interface RestApiService {

    @GET
    fun scanQr(info:String?):Single<RespScanning>

}
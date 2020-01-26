package com.yup.manager.data.dataSources.rest

import com.yup.manager.domain.entities.user.LoginReq
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface RestApi {

    @POST("/v1/manager/login")
    fun signIn(@Body signInForm: LoginReq):Single<ResponseBody>

}
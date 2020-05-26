package com.yup.manager.data.repositories

import com.yup.manager.data.rest.RestApiService
import com.yup.manager.domain.entities.user.LoginReq
import com.yup.manager.domain.entities.user.LoginResp
import com.yup.manager.domain.repositories.IUserRepository
import io.reactivex.Single
import javax.inject.Inject

//created by Ilmir Shagabiev

class UserRepositoryImpl @Inject constructor(private val restApiService: RestApiService) :
    IUserRepository {

    override fun getProfile(token: String) = restApiService.getMyInfo("Bearer $token")

    override fun login(login: String, password: String): Single<LoginResp> =
        restApiService.signIn(LoginReq(login, password))
}
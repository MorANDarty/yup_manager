package com.yup.manager.data.repositories

import com.yup.manager.data.rest.RestApiService
import com.yup.manager.domain.entities.user.LoginReq
import com.yup.manager.domain.entities.user.LoginResp
import com.yup.manager.domain.entities.user.User
import com.yup.manager.domain.repositories.IUserRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

//created by Ilmir Shagabiev

class UserRepositoryImpl @Inject constructor(private val restApiService: RestApiService):
    IUserRepository{

    override fun getProfile(): Single<User> {
        return  User("Виталий Цаль", "3.7", "1231").toSingle()

        //TODO implement
    }

    override fun login(login: String, password: String): Single<LoginResp> =
        restApiService.signIn(LoginReq(login, password))
}
package com.yup.manager.domain.repositories

import com.yup.manager.domain.entities.user.LoginResp
import com.yup.manager.domain.entities.user.User
import io.reactivex.Single

//created by Ilmir Shagabiev

interface IUserRepository {

    fun getProfile(): Single<User>

    fun login(login:String, password:String):Single<LoginResp>
}
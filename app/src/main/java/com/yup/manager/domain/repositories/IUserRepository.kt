package com.yup.manager.domain.repositories

import com.yup.manager.domain.entities.user.LoginResp
import com.yup.manager.domain.entities.user.User
import com.yup.manager.domain.entities.user.UserInfoResp
import io.reactivex.Single

//created by Ilmir Shagabiev

interface IUserRepository {

    fun getProfile(token:String): Single<UserInfoResp>

    fun login(login:String, password:String):Single<LoginResp>
}
package com.yup.manager.domain.repositories

import com.yup.manager.domain.entities.user.User
import io.reactivex.Single

//created by Ilmir Shagabiev

interface IUserRepository {

    fun getProfile(): Single<User>
}
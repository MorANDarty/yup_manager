package com.yup.manager.data.repositories

import com.yup.manager.domain.entities.user.User
import com.yup.manager.domain.repositories.IUserRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle

//created by Ilmir Shagabiev

class UserRepositoryImpl : IUserRepository{

    override fun getProfile(): Single<User> {
        return  User("Виталий Цаль", "3.7", "1231").toSingle()
    }
}
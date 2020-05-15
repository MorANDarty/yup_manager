package com.yup.manager.app.interactors

import com.yup.manager.domain.repositories.IUserRepository
import io.reactivex.schedulers.Schedulers


class LoginInteractor(private val userRepo:IUserRepository) {

    fun login(login:String, password:String) = userRepo.login(login, password)

}
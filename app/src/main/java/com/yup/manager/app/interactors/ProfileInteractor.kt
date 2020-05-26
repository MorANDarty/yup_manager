package com.yup.manager.app.interactors

import com.yup.manager.domain.repositories.IUserRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//created by Ilmir Shagabiev

class ProfileInteractor @Inject constructor(private val userRepo: IUserRepository) {

    fun getProfile(token:String) = userRepo.getProfile(token).subscribeOn(Schedulers.io())
}
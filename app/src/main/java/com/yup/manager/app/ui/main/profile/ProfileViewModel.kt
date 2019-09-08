package com.yup.manager.app.ui.main.profile

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.interactors.ProfileInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.data.utils.Response
import com.yup.manager.domain.entities.user.User
import io.reactivex.android.schedulers.AndroidSchedulers

import javax.inject.Inject

//created by Ilmir Shagabiev

class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor
) : BaseViewModel() {

    var profileLiveData = MutableLiveData<Response<User>>()

    fun getProfile() {
        showLoadingLiveData.value = true
        disposables.add(
            profileInteractor.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showLoadingLiveData.value = false }
                .subscribe({
                    profileLiveData.value = Response.success(it)
                }, {
                    profileLiveData.value = Response.error(it)
                })
        )
    }
}
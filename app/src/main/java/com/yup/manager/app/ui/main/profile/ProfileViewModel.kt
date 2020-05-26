package com.yup.manager.app.ui.main.profile

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.interactors.ProfileInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.data.SessionManager
import com.yup.manager.data.utils.Response
import com.yup.manager.domain.entities.user.UserInfoResp
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

//created by Ilmir Shagabiev

class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val sm: SessionManager
) : BaseViewModel() {

    var profileLiveData = MutableLiveData<Response<UserInfoResp>>()

    fun getProfile() {
        showLoadingLiveData.value = true
        sm.getToken()?.let {
            profileInteractor.getProfile(it)
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showLoadingLiveData.value = false }
                .subscribe({
                    profileLiveData.value = Response.success(it)
                }, {
                    profileLiveData.value = Response.error(it)
                })
        }?.let { disposables.add(it) }
    }
}
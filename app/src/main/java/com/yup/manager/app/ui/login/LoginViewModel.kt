package com.yup.manager.app.ui.login

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.interactors.LoginInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.data.SessionManager
import com.yup.manager.data.utils.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val interactor: LoginInteractor,
    private val sm: SessionManager
) : BaseViewModel() {

    val loginLiveData = MutableLiveData<Response<Boolean>>()
    val isLoggedInLiveData = MutableLiveData<Response<Boolean>>()

    fun login(login: String, password: String) {
        disposables.add(
            interactor.login(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        sm.putToken(it.token)
                        loginLiveData.value = Response(true, null)
                        loginLiveData.value = Response(null, null)
                    },
                    {
                        loginLiveData.value = Response(null, it)
                        loginLiveData.value = Response(null, null)
                    })
        )
    }

    fun isLoggedIn() {
        val token = sm.getToken()
        if (token == null || token.isEmpty()) {
            isLoggedInLiveData.value = Response(false, null)
        } else isLoggedInLiveData.value = Response(true, null)
        isLoggedInLiveData.value = Response(null, null)
    }
}
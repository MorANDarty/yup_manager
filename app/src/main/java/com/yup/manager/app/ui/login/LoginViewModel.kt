package com.yup.manager.app.ui.login

import androidx.lifecycle.MutableLiveData
import com.yup.manager.app.interactors.LoginInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.app.utils.simpleLog
import com.yup.manager.data.SessionManager
import com.yup.manager.data.utils.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okio.utf8Size
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
                        sm.createSession(null, null, null, it.token)
                        loginLiveData.value = Response(true, null)
                        loginLiveData.value = Response(null, null)
                    },
                    {
                        loginLiveData.value = Response(null, it)
                        it.message?.let { it1 -> simpleLog(it1) }
                        it.printStackTrace()
                        loginLiveData.value = Response(null, null)
                    })
        )
    }

    fun isLoggedIn() {
        val token = sm.getToken()
        simpleLog("token from loginViewModel = $token")

        if(token!=null && token.isNotEmpty()){
            isLoggedInLiveData.value = Response(true, null)
        }else{
            isLoggedInLiveData.value = Response(false, null)
        }
    }
}
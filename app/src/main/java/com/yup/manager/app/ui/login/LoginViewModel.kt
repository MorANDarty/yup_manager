package com.yup.manager.app.ui.login

import com.yup.manager.app.interactors.LoginInteractor
import com.yup.manager.app.ui.base.BaseViewModel
import com.yup.manager.data.SessionManager
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val ordersInteractor: LoginInteractor,
    private val sm:SessionManager
) : BaseViewModel() {




}
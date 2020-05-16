package com.yup.manager.app.ui.main

import android.os.Bundle
import com.arellomobile.mvp.MvpView

//created by Ilmir Shagabiev

interface MainView : MvpView {

    fun onBackFromOrderInfo()

    fun showOrderInfo(bundle:Bundle)

    fun showOrdersList()

    fun showProfile()

}
package com.yup.manager.app.ui.main

import com.arellomobile.mvp.MvpView

//created by Ilmir Shagabiev

interface MainView : MvpView {

    fun setNewCurrent(position:Int)

    fun showDialog(id: Int)

}
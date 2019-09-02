package com.yup.manager.app.di.modules

import android.app.Application
import com.yup.manager.app.di.scopes.AppScope
import com.yup.manager.app.ui.main.MainViewModel
import dagger.Module
import dagger.Provides


//created by Ilmir Shagabiev

@Module
class ApplicationModule (private val application:Application){

    //TODO
    @Provides
    @AppScope
    fun provideMainPresenter():MainViewModel = MainViewModel()

}
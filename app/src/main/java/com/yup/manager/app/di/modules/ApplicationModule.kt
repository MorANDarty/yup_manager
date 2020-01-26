package com.yup.manager.app.di.modules

import android.app.Application
import com.yup.manager.app.di.scopes.AppScope
import com.yup.manager.app.di.scopes.DataScope
import com.yup.manager.app.interactors.OrdersInteractor
import com.yup.manager.app.ui.main.MainViewModel
import com.yup.manager.app.ui.main.orders.OrderViewModel
import com.yup.manager.data.SessionManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


//created by Ilmir Shagabiev

@Module
class ApplicationModule (private val application:Application){

    @Provides
    @AppScope
    fun provideSessionManager(): SessionManager = SessionManager(application)

}
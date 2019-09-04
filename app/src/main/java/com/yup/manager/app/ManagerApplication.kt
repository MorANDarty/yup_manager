package com.yup.manager.app

import android.app.Application
import androidx.annotation.NonNull
import com.yup.manager.app.di.components.ApplicationComponent
import com.yup.manager.app.di.components.DaggerApplicationComponent
import com.yup.manager.app.di.components.DaggerDataComponent
import com.yup.manager.app.di.components.DataComponent
import com.yup.manager.app.di.modules.ApplicationModule
import com.yup.manager.app.di.modules.DataModule

//created by Ilmir Shagabiev

class ManagerApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
        initActivityComponent()
        INSTANCE = this
    }

    private fun initActivityComponent() {
    }

    private fun initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dataComponent(initDataComponent())
            .build()
    }

    private fun initDataComponent(): DataComponent = DaggerDataComponent.builder()
        .dataModule(DataModule(this))
        .build()

    companion object {
        private lateinit var INSTANCE: ManagerApplication
        @JvmStatic
        fun get(): ManagerApplication = INSTANCE

        private var applicationComponent: ApplicationComponent? = null
            private set
    }

    @NonNull
    fun getAppComponent() = applicationComponent
}
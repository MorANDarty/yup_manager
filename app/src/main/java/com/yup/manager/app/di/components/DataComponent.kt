package com.yup.manager.app.di.components

import android.content.Context
import com.yup.manager.app.di.modules.DataModule
import com.yup.manager.app.di.scopes.DataScope
import dagger.Component

//created by Ilmir Shagabiev

@DataScope
@Component(modules = [DataModule::class])
interface DataComponent {

    val context: Context
    /*val userRepo: IUserRepository
    val orderRepo: IOrderRepository*/
}
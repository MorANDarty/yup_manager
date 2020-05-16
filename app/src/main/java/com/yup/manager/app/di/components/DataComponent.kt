package com.yup.manager.app.di.components

import android.content.Context
import com.yup.manager.app.di.modules.DataModule
import com.yup.manager.app.di.scopes.DataScope
import com.yup.manager.data.rest.RestApiService
import com.yup.manager.domain.repositories.IOrderRepository
import com.yup.manager.domain.repositories.IUserRepository
import dagger.Component

//created by Ilmir Shagabiev

@DataScope
@Component(modules = [DataModule::class])
interface DataComponent {

    val context: Context
   /* val userRepo: IUserRepository*/
    val orderRepo: IOrderRepository
    val userRepo:IUserRepository
    val api:RestApiService
}
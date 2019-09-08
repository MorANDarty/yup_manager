package com.yup.manager.app.di.modules

import android.content.Context
import com.yup.manager.app.di.scopes.DataScope
import com.yup.manager.data.repositories.OrderRepositoryImpl
import com.yup.manager.data.repositories.UserRepositoryImpl
import com.yup.manager.domain.repositories.IOrderRepository
import com.yup.manager.domain.repositories.IUserRepository
import dagger.Module
import dagger.Provides

//created by Ilmir Shagabiev

@Module
class DataModule(private val context: Context) {

    @Provides
    @DataScope
    fun provideContext() = context

    @Provides
    @DataScope
    fun provideOrderRepository():IOrderRepository = OrderRepositoryImpl()

    @Provides
    @DataScope
    fun provideUserRepository():IUserRepository = UserRepositoryImpl()

}

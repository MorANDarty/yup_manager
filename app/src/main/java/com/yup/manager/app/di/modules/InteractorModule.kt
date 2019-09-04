package com.yup.manager.app.di.modules

import com.yup.manager.app.di.scopes.AppScope
import com.yup.manager.app.interactors.OrdersInteractor
import com.yup.manager.domain.repositories.IOrderRepository
import dagger.Module
import dagger.Provides

//created by Ilmir Shagabiev

@Module
class InteractorModule {

    @Provides
    @AppScope
    fun provideOrderInteractor(orderRepo:IOrderRepository):OrdersInteractor = OrdersInteractor(orderRepo)
}
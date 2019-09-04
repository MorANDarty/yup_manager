package com.yup.manager.app.di.components

import android.content.Context
import com.yup.manager.app.ui.main.MainActivity
import com.yup.manager.app.di.modules.ApplicationModule
import com.yup.manager.app.di.modules.InteractorModule
import com.yup.manager.app.di.modules.ViewModelFactoryModule
import com.yup.manager.app.di.scopes.AppScope
import com.yup.manager.app.ui.main.orders.OrdersFragment
import com.yup.manager.domain.repositories.IUserRepository
import dagger.Component

//created by Ilmir Shagabiev

@AppScope
@Component(
    dependencies = [(DataComponent::class)],
    modules = [(ApplicationModule::class), (InteractorModule::class), (ViewModelFactoryModule::class)]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(ordersFragment: OrdersFragment)

    val context:Context
    /*val userRepo:IUserRepository*/

}
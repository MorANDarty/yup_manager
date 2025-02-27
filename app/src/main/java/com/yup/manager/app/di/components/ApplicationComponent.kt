package com.yup.manager.app.di.components

import android.content.Context
import com.yup.manager.app.ui.main.MainActivity
import com.yup.manager.app.di.modules.ApplicationModule
import com.yup.manager.app.di.modules.InteractorModule
import com.yup.manager.app.di.modules.ViewModelFactoryModule
import com.yup.manager.app.di.modules.ViewModelModule
import com.yup.manager.app.di.scopes.AppScope
import com.yup.manager.app.ui.login.LoginActivity
import com.yup.manager.app.ui.main.orders.OrdersFragment
import com.yup.manager.app.ui.main.orders.orderInfo.OrderInfoFragment
import com.yup.manager.app.ui.main.profile.ProfileFragment
import com.yup.manager.app.ui.qrScanning.ScanningActivity
import com.yup.manager.data.SessionManager
import com.yup.manager.domain.repositories.IUserRepository
import dagger.Component

//created by Ilmir Shagabiev

@AppScope
@Component(
    dependencies = [(DataComponent::class)],
    modules = [(ApplicationModule::class), (InteractorModule::class), (ViewModelFactoryModule::class), (ViewModelModule::class)]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(ordersFragment: OrdersFragment)
    fun inject(profileFragment:ProfileFragment)
    fun inject(qrFragment:ScanningActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(orderInfoFragment: OrderInfoFragment)

    val context:Context
    val sm:SessionManager
/*
    val userRepo:IUserRepository
*/

}
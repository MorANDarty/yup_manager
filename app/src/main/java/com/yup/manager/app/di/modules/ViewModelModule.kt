package com.yup.manager.app.di.modules

import androidx.lifecycle.ViewModel
import com.yup.manager.app.ui.login.LoginViewModel
import com.yup.manager.app.ui.main.orders.OrderViewModel
import com.yup.manager.app.ui.main.orders.orderInfo.OrderInfoViewModel
import com.yup.manager.app.ui.main.profile.ProfileViewModel
import com.yup.manager.app.ui.qrScanning.QrScanningViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

//created by Ilmir Shagabiev

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindOrderViewModel(orderViewModel: OrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QrScanningViewModel::class)
    abstract fun bindQrScanningViewModel(qrViewModel: QrScanningViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderInfoViewModel::class)
    abstract fun bindOrderInfoViewModel(orderInfoViewModel: OrderInfoViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
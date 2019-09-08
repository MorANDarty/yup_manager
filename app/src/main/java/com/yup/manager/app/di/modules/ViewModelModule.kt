package com.yup.manager.app.di.modules

import androidx.lifecycle.ViewModel
import com.yup.manager.app.ui.main.orders.OrderViewModel
import com.yup.manager.app.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.MapKey
import kotlin.reflect.KClass

//created by Ilmir Shagabiev

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindOrderViewModele(orderViewModel: OrderViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModele(profileViewModel: ProfileViewModel):ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
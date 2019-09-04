package com.yup.manager.app.di.modules

import androidx.lifecycle.ViewModelProvider
import com.yup.manager.app.ui.ViewModelFactory
import dagger.Binds
import dagger.Module


//created by Ilmir Shagabiev
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}

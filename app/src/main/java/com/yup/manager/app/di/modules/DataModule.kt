package com.yup.manager.app.di.modules

import android.content.Context
import com.yup.manager.app.di.scopes.DataScope
import dagger.Module
import dagger.Provides

//created by Ilmir Shagabiev

@Module
class DataModule(private val context: Context) {

    @Provides
    @DataScope
    fun provideContext() = context

}

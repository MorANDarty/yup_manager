package com.yup.manager.app.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.yup.manager.BuildConfig
import com.yup.manager.app.di.scopes.DataScope
import com.yup.manager.data.repositories.OrderRepositoryImpl
import com.yup.manager.data.repositories.UserRepositoryImpl
import com.yup.manager.data.rest.RestApiService
import com.yup.manager.data.utils.UrlProvider
import com.yup.manager.domain.repositories.IOrderRepository
import com.yup.manager.domain.repositories.IUserRepository
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//created by Ilmir Shagabiev

@Module
class DataModule(private val context: Context) {

    @Provides
    @DataScope
    fun provideContext() = context

    @Provides
    @DataScope
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @DataScope
    fun provideUrlProvider(): UrlProvider = UrlProvider()

    @Provides
    @DataScope
    fun providesLoggingInterceptor(): LoggingInterceptor = LoggingInterceptor.Builder()
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .request("REQUEST")
        .response("RESPONSE")
        .build()

    @Provides
    @DataScope
    fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @DataScope
    fun providesRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        urlProvider: UrlProvider
    ): Retrofit = Retrofit.Builder()
        .baseUrl(urlProvider.getDevServer())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()


    @Provides
    @DataScope
    fun provideRestApiService(retrofit: Retrofit): RestApiService =
        retrofit.create(RestApiService::class.java)

    @Provides
    @DataScope
    fun provideUserRepository(restApiService: RestApiService): IUserRepository =
        UserRepositoryImpl(restApiService)

    @Provides
    @DataScope
    fun provideOrderRepository(restApiService: RestApiService): IOrderRepository =
        OrderRepositoryImpl(restApiService)

}

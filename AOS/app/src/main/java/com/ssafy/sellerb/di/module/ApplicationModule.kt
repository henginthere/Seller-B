package com.ssafy.sellerb.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.di.ApplicationContext
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.CoroutineDispatchersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: SellerBApplication) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideCoroutineDispatchersProvider(): CoroutineDispatchers =
        CoroutineDispatchersProvider()

    @Provides
    @Singleton
    fun provideSharePreferences(): SharedPreferences =
        application.getSharedPreferences("sellerB-pref", Context.MODE_PRIVATE)

}
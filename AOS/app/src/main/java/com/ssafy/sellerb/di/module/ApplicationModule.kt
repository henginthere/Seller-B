package com.ssafy.sellerb.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.data.remote.NetworkService
import com.ssafy.sellerb.data.remote.Networking
import com.ssafy.sellerb.di.ApplicationContext
import com.ssafy.sellerb.util.Constants
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchersProvider
import com.ssafy.sellerb.util.network.NetworkHelper
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

    @Provides
    @Singleton
    fun provideNetworkService() : NetworkService =
        Networking.create(
            Constants.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024
        )

    @Provides
    @Singleton
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

}
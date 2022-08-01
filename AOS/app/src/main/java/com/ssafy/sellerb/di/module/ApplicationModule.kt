package com.ssafy.sellerb.di.module

import android.app.Application
import android.content.Context
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.di.ApplicationContext
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
}
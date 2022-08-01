package com.ssafy.sellerb.di.component

import android.app.Application
import android.content.Context
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.di.ApplicationContext
import com.ssafy.sellerb.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: SellerBApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context
}
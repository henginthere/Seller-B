package com.ssafy.sellerb.di

import android.app.Application
import com.ssafy.sellerb.SellerB
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(sellerB: SellerB)
}
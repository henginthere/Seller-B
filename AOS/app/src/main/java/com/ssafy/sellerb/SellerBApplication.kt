package com.ssafy.sellerb

import android.app.Application
import com.ssafy.sellerb.di.component.ApplicationComponent
import com.ssafy.sellerb.di.component.DaggerApplicationComponent
import com.ssafy.sellerb.di.module.ApplicationModule


class SellerBApplication : Application(){

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies(){
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    fun setComponent(applicationComponent: ApplicationComponent){
        this.applicationComponent =applicationComponent
    }

}
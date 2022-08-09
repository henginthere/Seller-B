package com.ssafy.sellerb

import android.app.Application
import android.content.SharedPreferences
import com.ssafy.sellerb.di.component.ApplicationComponent
import com.ssafy.sellerb.di.component.DaggerApplicationComponent
import com.ssafy.sellerb.di.module.ApplicationModule
import com.ssafy.webrtc.openvidu.LocalParticipant
import javax.inject.Inject


class SellerBApplication : Application(){

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var preferences: SharedPreferences

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

}
package com.ssafy.sellerb

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.sellerb.di.component.ApplicationComponent
import com.ssafy.sellerb.di.component.DaggerApplicationComponent
import com.ssafy.sellerb.di.module.ApplicationModule
import com.ssafy.sellerb.ui.main.MainActivity
import com.ssafy.sellerb.util.Constants
import com.ssafy.webrtc.openvidu.LocalParticipant
import javax.inject.Inject


class SellerBApplication : Application(){

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        // FCM 토큰 수신
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(MainActivity.TAG, "FCM 토큰 얻기에 실패하였습니다.", task.exception)
                return@OnCompleteListener
            }
            //uploadToken(task.result!!)

            // token log 남기기
            Log.d(MainActivity.TAG, "token: ${task.result ?: "task.result is null"}")
        })
        createNotificationChannel(Constants.CHANNEL_ID, "sellerb")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    // Notification 수신을 위한 채널 추가
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun injectDependencies(){
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}
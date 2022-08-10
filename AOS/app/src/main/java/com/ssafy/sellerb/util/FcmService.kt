package com.ssafy.sellerb.util

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ssafy.sellerb.ui.main.MainActivity
import com.ssafy.sellerb.util.Constants.CHANNEL_ID


class FcmService : FirebaseMessagingService() {

    companion object{
        private const val TAG = "FcmService"
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        //uploadToken(token)

        // 새로운 토큰 로그 남기기
        Log.d(TAG, "onNewToken: $token")
    }

    // Foreground에서 Push Service를 받기위해 Notification 설정
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "onMessageReceived: ")

        remoteMessage.notification.let { message ->
            val messageTitle = message!!.title
            val messageContent = message!!.body

            val mainIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            //mainIntent.putExtra("content", messageContent.toString())
            //noti = messageContent.toString()

            val mainPendingIntent =
                PendingIntent.getActivity(this, 0, mainIntent, 0)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(messageTitle)
                .setContentText(messageContent)
                .setAutoCancel(true)
                .setContentIntent(mainPendingIntent)

            NotificationManagerCompat.from(this).apply {
                notify(101, builder.build())
            }
        }
    }

}
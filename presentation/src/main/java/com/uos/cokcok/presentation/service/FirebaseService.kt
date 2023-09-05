package com.uos.cokcok.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.orhanobut.logger.Logger
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.MainActivity
import kotlin.random.Random

class FirebaseService : FirebaseMessagingService() {

    //최초 설치시 토큰이 한번 발급되고 나서 onNewToken 이 불려지지 않는다.
    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
    }

    //포그라운드 상태인 앱에서 알림 메시지 또는 데이터 메시지를 수신하려면 onMessageReceived 콜백을 처리하는 코드를 작성해야 한다.
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Logger.d("Message: $message")
        message.notification?.let {
            showNotification(messageTitle = it.title, messageBody = it.body)
        }
    }

    private fun showNotification(messageTitle: String?, messageBody: String?) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        createNotificationChannel(notificationManager)

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntentFlag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE or FLAG_ONE_SHOT else FLAG_ONE_SHOT

        val pendingIntent = PendingIntent.getActivity(this, notificationID, intent, pendingIntentFlag)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            // 노티 알림 클릭시 알림 사라짐
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationID, notification)
    }

    //Oreo(26) 이상 버전에서만 호출 필요
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_HIGH).apply {
            description = CHANNEL_DESCRIPTION
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val TAG = "FirebaseService"
        private const val CHANNEL_ID = "fcm_default_channel"
        private const val CHANNEL_NAME = "SlothNotification"
        private const val CHANNEL_DESCRIPTION = "Channel For Sloth Notification"
    }
}
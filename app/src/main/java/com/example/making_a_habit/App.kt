package com.example.making_a_habit

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

/**
 * Created by Jaehyeon on 2022/09/03.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }


    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "alarm_id",
            "alarm_name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "alarm test"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
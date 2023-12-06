package com.making.making_a_habit.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.making.making_a_habit.R
import com.making.making_a_habit.ui.activity.MainActivity

class NotificationService(
    private val context: Context
) {

    private val manager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(message: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, "alarm_id")
            .setSmallIcon(R.drawable.ic_launcher_foreground2)
            .setContentTitle("오늘의 습관을 체크해 주세요!")
            .setContentText("아직 체크 안 한 습관이 있지 않을까요? 한 번 확인하러 가요!")
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)  // 클릭시 푸시 알림 사라짐
            .build()

        //manager.notify(1, notification)
    }

}
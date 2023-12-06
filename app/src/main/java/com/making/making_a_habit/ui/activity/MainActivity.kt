package com.making.making_a_habit.ui.activity

import android.app.PendingIntent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.making.making_a_habit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pIntent: PendingIntent

    init {
        Log.e("TAG", "init: ", )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

/*
    /**** 푸시알림 ****/
    @SuppressLint("ShortAlarm")
    private fun setPushNotification(){
        pIntent = Intent(this, AlarmBroadCastReceiver::class.java).apply {
            putExtra("data", "00")
        }.let {
            PendingIntent.getBroadcast(applicationContext, 0, it,  PendingIntent.FLAG_IMMUTABLE)
        }

        val calendar = Calendar.getInstance()
        if(Calendar.getInstance().after(calendar)){
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        manager.setRepeating(AlarmManager.RTC, setBaseTime(20).timeInMillis, AlarmManager.INTERVAL_DAY, pIntent)
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setBaseTime(3).timeInMillis, AlarmManager.INTERVAL_DAY, pIntent)
        print(setBaseTime(12).toString())
        //Log.e("호이 : ", setBaseTime(12).toString())
    }
    /** 푸시알림 시간 설정 **/
    private fun setBaseTime(baseHour: Int): Calendar {
        val today = LocalDate.now()
        val todayCalendar = Calendar.getInstance()
        val baseTime = Calendar.getInstance().apply {
            set(today.year, today.monthValue -1, today.dayOfMonth, baseHour, 1)
            set(Calendar.YEAR, today.year)
            set(Calendar.MONTH, today.monthValue-1)
            set(Calendar.DATE, today.dayOfMonth)
            set(Calendar.HOUR_OF_DAY, baseHour)
            set(Calendar.MINUTE, 30)
        }

        Log.e("baseHour :", baseHour.toString())

        return if (todayCalendar.time.time < baseTime.time.time) {
            Calendar.getInstance().apply {
                set(today.year, today.monthValue -1 , today.dayOfMonth - 1, baseHour, 1)
                set(Calendar.YEAR, today.year)
                set(Calendar.MONTH, today.monthValue-1)
                set(Calendar.DATE, today.dayOfMonth-1)
                set(Calendar.HOUR_OF_DAY, baseHour)
                set(Calendar.MINUTE, 30)
            }
        } else {
            baseTime
        }
    }
 */

}
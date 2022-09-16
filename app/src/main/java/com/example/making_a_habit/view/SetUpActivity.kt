package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.AlarmBroadCastReceiver
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.databinding.ListDonehabitPageBinding
import com.example.making_a_habit.databinding.SetupPageBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity.setActivityTitle
import java.time.LocalDate
import java.util.*

class SetUpActivity : AppCompatActivity() {

    /***** veiwBinding *****/
    private lateinit var binding: SetupPageBinding
    /***** 푸시알림 부분 *****/
    private val manager: AlarmManager by lazy {
        getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    private lateinit var pIntent: PendingIntent

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /***** veiwBinding *****/
        binding = SetupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 뒤로가기 페이지 화면 전환 *****/
        binding.backBtnSetuppage.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        /***** switch 상태 확인 후 switchbutton 상태 변경 *****/
        val sharedPreference =  getSharedPreferences("switch_state",Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        var first = sharedPreference.getBoolean("switch_state", true)
        if(first == true){
            Log.e("Is switch state?", "true")
            binding.alarmSwitch.setChecked(true)
            setPushNotification()
        }
        else{
            Log.e("Is switch state?", "false")
            binding.alarmSwitch.setChecked(false)
            cancelAlarm()
        }
        /***** switchbutton 클릭시 상태 변경 및 데이터 변경 *****/
        binding.alarmSwitch.setOnCheckedChangeListener{CompoundButton, onSwitch ->
            /** switch 켜짐 **/
            if (onSwitch){
                Log.e("푸시알림 스위치 상태 : ", onSwitch.toString())
                setPushNotification()
                editor.putBoolean("switch_state", true)
                editor.apply()
            }
            /** switch 꺼짐 **/
            else{
                Log.e("푸시알림 스위치 상태 : ", onSwitch.toString())
                cancelAlarm()
                editor.putBoolean("switch_state", false)
                editor.apply()
            }
        }

        /***** 오픈소스 라이선스 *****/
        binding.opensourceLayout.setOnClickListener { it ->
            Intent(applicationContext, OssLicensesMenuActivity::class.java).also {
                startActivity(it)
            }
            true
        }

    } /** onCreate **/

    /***** 뒤로가기 페이지 화면 전환 *****/
    override fun onBackPressed() {
        var intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }

    /***** 푸시알림 *****/
    @SuppressLint("ShortAlarm")
    private fun setPushNotification(){

        pIntent = Intent(this, AlarmBroadCastReceiver::class.java).apply {
            putExtra("data", "02")
        }.let {
            PendingIntent.getBroadcast(applicationContext, 0, it,  PendingIntent.FLAG_IMMUTABLE)
        }

        manager.setRepeating(
            AlarmManager.RTC_WAKEUP, setBaseTime(21).timeInMillis, AlarmManager.INTERVAL_DAY, pIntent)
    }
    /*** 푸시알림 시간 설정 ***/
    private fun setBaseTime(baseHour: Int): Calendar {
        val today = LocalDate.now()
        val todayCalendar = Calendar.getInstance()
        val baseTime = Calendar.getInstance().apply {
            set(today.year, today.monthValue -1, today.dayOfMonth, baseHour, 0)
        }

        return if (todayCalendar.time.time < baseTime.time.time) {
            Calendar.getInstance().apply {
                set(today.year, today.monthValue -1 , today.dayOfMonth - 1, baseHour, 0)
            }
        } else {
            baseTime
        }
    }
    /*** 푸시알림 취소 ***/
    private fun cancelAlarm() {
        val receiverIntent = Intent(getApplication(), AlarmBroadCastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE)
        manager?.cancel(pendingIntent)
    }
}
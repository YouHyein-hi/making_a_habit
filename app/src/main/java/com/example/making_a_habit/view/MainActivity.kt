package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.size
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.making_a_habit.AlarmReceiver
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.view.adapter.MainRecyclerViewAdapter
import com.example.making_a_habit.viewmodel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModels()

    /***** veiwBinding *****/
    private lateinit var binding: ActivityMainBinding
    /***** Adapter *****/
    private val adapter: MainRecyclerViewAdapter =  MainRecyclerViewAdapter { habit ->
        // put extras of contact info & start CreatingHabitActivity
    }
    /***** 푸시알림 부분 *****/
    internal var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***** veiwBinding *****/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /***** 푸시알림 부분 *****/
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager?
        setAlarm(0, 1)

        /***** RecyclerView 부분 *****/
        binding.mainRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
        binding.mainRecyclerView.setHasFixedSize(true)


        /***** 화면 전환 부분  *****/
        /***** 진행 습관 3개 제한 *****/
        binding.creatingPageBtn.setOnClickListener {
            if(binding.mainRecyclerView.size == 3){
                Toast.makeText(this, "진행 습관은 3개만 가능합니다!", Toast.LENGTH_SHORT).show()
                println("진행 습관은 3개만 가능합니다!")
            }
            else if(binding.mainRecyclerView.size < 3){
                var intent = Intent(this, CreatinghabitActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }
        binding.setupPageBtn.setOnClickListener{
            var intent = Intent(this, SetUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        binding.clockPageBtn.setOnClickListener{
            var intent = Intent(this, ListDoneHabitActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


        /***** ViewModel 부분 *****/
        //val habitViewModel: HabitViewModel by viewModels()


        //main_recyclerView.
    }  // onCreate

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            adapter.sethabit(mainViewModel.getAll())
        }
    }

    /***** 푸시알림 부분 *****/
    private fun cancelAlarm() {
        val receiverIntent = Intent(getApplication(), AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager?.cancel(pendingIntent)
    }

    private fun setAlarm(hour: Int, minute: Int) {
        //옵션값에 따라서, 푸시 설정이 되지 않을 수 있도록 함
        //if (!pushAvailable.value) return
        println("setAlarm 수행됨")

        //AlarmReceiver에 값 전달
        val receiverIntent = Intent(getApplication(), AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, receiverIntent, PendingIntent.FLAG_IMMUTABLE)

        //alarm 등록 전, 이전 push cancel
        alarmManager?.cancel(pendingIntent)

        // Set the alarm to start at time and minute
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 11)
            set(Calendar.SECOND, 11)
        }

        if (calendar.time < Date()) { //설정한 시간에 따라, 알람이 설정이 안될 수 있으므로 달 첫번째 부터의 시간을 설정
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }


}
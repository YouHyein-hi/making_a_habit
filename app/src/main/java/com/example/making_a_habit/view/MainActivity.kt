package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.view.adapter.MainRecyclerViewAdapter
import com.example.making_a_habit.viewmodel.MainViewModel
import com.example.making_a_habit.AlarmBroadCastReceiver
import java.time.LocalDate
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
    private val manager: AlarmManager by lazy {
        getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    private lateinit var pIntent: PendingIntent

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***** veiwBinding *****/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 앱 최초 실행 확인 *****/
        val sharedPreference =  getSharedPreferences("isFirst",Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        var first = sharedPreference.getBoolean("isFirst", false)
        if(first == false){
            Log.e("Is first Time?", "first")
            editor.putBoolean("isFirst", true)
            editor.apply()
            //setPushNotification()
        }
        else{
            Log.e("Is first Time?", "not first");
        }


        /***** RecyclerView 부분 *****/
        binding.mainRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
        binding.mainRecyclerView.setHasFixedSize(true)


        /***** 화면 전환 부분  *****/
        /** 진행 습관 3개 제한 **/
        binding.creatingPageBtn.setOnClickListener {
            if(binding.mainRecyclerView.size == 3){
                Toast.makeText(this, "진행 습관은 3개만 가능합니다!", Toast.LENGTH_SHORT).show()
                println("진행 습관은 3개만 가능합니다!")
            }
            else if(binding.mainRecyclerView.size < 3){
                var intent = Intent(this, CreatinghabitActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }
        }
        binding.setupPageBtn.setOnClickListener{
            var intent = Intent(this, SetUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()

        }
        binding.clockPageBtn.setOnClickListener{
            var intent = Intent(this, ListDoneHabitActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }



    }  // onCreate

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            adapter.sethabit(mainViewModel.getAll())
        }
    }

    /***** 푸시알림 부분 *****/
    @SuppressLint("ShortAlarm")
    private fun setPushNotification(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pIntent = Intent(this, AlarmBroadCastReceiver::class.java).apply {
            putExtra("data", "02")
        }.let {
            PendingIntent.getBroadcast(applicationContext, 0, it,  PendingIntent.FLAG_IMMUTABLE)
        }

        manager.setRepeating(AlarmManager.RTC_WAKEUP, setBaseTime(21).timeInMillis,AlarmManager.INTERVAL_DAY, pIntent)
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

}
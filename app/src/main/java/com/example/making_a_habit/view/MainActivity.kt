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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***** veiwBinding *****/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


}
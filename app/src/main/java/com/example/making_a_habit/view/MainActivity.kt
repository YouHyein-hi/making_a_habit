package com.example.making_a_habit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.R
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.HabitViewModel

class MainActivity : AppCompatActivity() {
    //private lateinit var habitViewModel : HabitViewModel
    val habitViewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /***** RecyclerView 부분 *****/
        val adapter = MainAdapter { habit ->
            // put extras of contact info & start AddActivity
        }

        val lm = LinearLayoutManager(this)
        val main_recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
        main_recyclerView.adapter = adapter
        main_recyclerView.layoutManager = lm
        main_recyclerView.setHasFixedSize(true)


        /***** 화면 전환 부분  -> 다시 수정할 예정 (임시!) *****/
        val creating_btn = findViewById<ImageButton>(R.id.creatingPage_btn) as ImageButton
        val setupPage_btn = findViewById<ImageView>(R.id.setupPage_btn)
        val clockPage_btn = findViewById<ImageButton>(R.id.clockPage_btn)

        creating_btn.setOnClickListener {
            var intent = Intent(this, CreatingHabitActivity::class.java)
            startActivity(intent)
        }
        setupPage_btn.setOnClickListener{
            var intent = Intent(this, SetUpActivity::class.java)
            startActivity(intent)
        }
        clockPage_btn.setOnClickListener{
            var intent = Intent(this, ClockActivity::class.java)
            startActivity(intent)
        }


        /***** ViewModel 부분 *****/
        //val habitViewModel: HabitViewModel by viewModels()
        habitViewModel.getAll().observe(this, Observer<List<Habit>>{ habits ->
            // update UI
            adapter.sethabit(habits!!)
            //adapter.
        })
    }  // onCreate
}

// 화면 전환 부분 수정
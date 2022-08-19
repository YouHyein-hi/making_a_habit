package com.example.making_a_habit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.making_a_habit.R
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.HabitViewModel

class MainActivity : AppCompatActivity() {
    //private lateinit var habitViewModel : HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val habitViewModel: HabitViewModel by viewModels()
        habitViewModel.getAll().observe(this, Observer<List<Habit>>{ habits ->
            // update UI
        })


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
    }
}

// 화면 전환 부분 수정
package com.example.making_a_habit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.HabitViewModel
//import androidx.lifecycle.get

class MainActivity : AppCompatActivity() {
    val habitViewModel: HabitViewModel by viewModels()

    /***** veiwBinding *****/
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //habitViewModel = ViewModelProvider.of(this).get(HabitViewModel::class.java)
        /***** veiwBinding *****/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** RecyclerView 부분 *****/
        val adapter = MainAdapter { habit ->
            // put extras of contact info & start CreatingHabitActivity
        }

        val lm = LinearLayoutManager(this)
        val main_recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
        main_recyclerView.adapter = adapter
        main_recyclerView.layoutManager = lm
        main_recyclerView.setHasFixedSize(true)


        /***** 화면 전환 부분  -> 다시 수정할 예정 (임시!) *****/
        binding.creatingPageBtn.setOnClickListener {
            var intent = Intent(this, CreatinghabitActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        binding.setupPageBtn.setOnClickListener{
            var intent = Intent(this, SetUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        binding.clockPageBtn.setOnClickListener{
            var intent = Intent(this, ClockActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
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
package com.example.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.view.adapter.MainRecyclerViewAdapter
import com.example.making_a_habit.viewmodel.MainViewModel

//import androidx.lifecycle.get

class MainActivity : AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModels()

    /***** veiwBinding *****/
    private lateinit var binding: ActivityMainBinding
    private val adapter: MainRecyclerViewAdapter =  MainRecyclerViewAdapter { habit ->
        // put extras of contact info & start CreatingHabitActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //habitViewModel = ViewModelProvider.of(this).get(HabitViewModel::class.java)
        /***** veiwBinding *****/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** RecyclerView 부분 *****/

        binding.mainRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
        binding.mainRecyclerView.setHasFixedSize(true)


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


        //main_recyclerView.
    }  // onCreate

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            adapter.sethabit(mainViewModel.getAll())
        }
    }
}

// 화면 전환 부분 수정
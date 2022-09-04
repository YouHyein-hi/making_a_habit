package com.example.making_a_habit.view

import android.media.CamcorderProfile.getAll
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.databinding.ListDonehabitPageBinding
import com.example.making_a_habit.view.adapter.ListDoneHabitAdapter
import com.example.making_a_habit.view.adapter.MainRecyclerViewAdapter
import com.example.making_a_habit.viewmodel.ListDoneHabitViewModel

class ListDoneHabitActivity : AppCompatActivity() {
    val listDoneHabitViewModel: ListDoneHabitViewModel by viewModels()

    /***** veiwBinding *****/
    private lateinit var binding: ListDonehabitPageBinding
    /***** Adapter *****/
    private val adapter: ListDoneHabitAdapter =  ListDoneHabitAdapter { habit ->
        // put extras of contact info & start CreatingHabitActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***** veiwBinding *****/
        binding = ListDonehabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** RecyclerView 부분 *****/
        binding.listdonehabitRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.listdonehabitRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
        binding.listdonehabitRecyclerView.setHasFixedSize(true)

        /***** 화면 전환 부분  *****/
        binding.backBtnListdonehabitpage.setOnClickListener{
            finish()
        }

    } // onCreate

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            adapter.sethabit(listDoneHabitViewModel.getAll())
        }
    }
}
// TODO habitComplete에서 true 데이터만 가져오게 설정해야됨
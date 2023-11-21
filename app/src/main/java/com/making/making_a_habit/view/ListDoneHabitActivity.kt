package com.making.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.making.making_a_habit.databinding.ListDonehabitPageBinding
import com.making.making_a_habit.view.adapter.ListDoneHabitAdapter
import com.making.making_a_habit.viewmodel.ListDoneHabitViewModel

class ListDoneHabitActivity : AppCompatActivity() {

    /***** ViewModel *****/
    val listDoneHabitViewModel: ListDoneHabitViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: ListDonehabitPageBinding
    /***** Adapter *****/
    private val adapter : ListDoneHabitAdapter by lazy{ ListDoneHabitAdapter() }

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
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

    } // onCreate

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            adapter.sethabit(listDoneHabitViewModel.getAll().sortedWith(compareBy { it.habitDateEnd }))
        }
    }

}
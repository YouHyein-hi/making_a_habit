package com.example.making_a_habit.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.making_a_habit.databinding.DetailsHabitPageBinding
import com.example.making_a_habit.databinding.DetailsHabitPageBinding.inflate
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.HabitViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailHabitActivity : AppCompatActivity() {

    val habitViewModel: HabitViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: DetailsHabitPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /***** veiwBinding *****/
        binding = DetailsHabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnDetailshabitpage.setOnClickListener{
            finish()
        }
        binding.deleteBtnDetailshabitpage.setOnClickListener{

        }



        // TODO id 값으로 데이터 가져오기 하자.....할수있어....
        if(intent.hasExtra("data")) {
            // intent를 통해 클릭한 item habitId를 가져옴
            val habitId = intent.getIntExtra("data",0)
            println(habitId) // 잘 나오는지 확인
            CoroutineScope(Dispatchers.IO).launch {
                habitViewModel.getHabitId(intent.getIntExtra("data", 0))
                println(habitViewModel.loadAllByIds(intent.getIntExtra("data", 0)))
            }
        }



        /***** ViewModel 부분 *****/
        habitViewModel.getAll().observe(this, Observer<List<Habit>>{ habits ->
            // update UI
            //adapter.
        })

        //main_recyclerView.
    }  // onCreate
}
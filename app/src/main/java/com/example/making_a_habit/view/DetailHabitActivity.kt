package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.databinding.DetailsHabitPageBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.adapter.Habit3RoundAdapter
import com.example.making_a_habit.view.dialog.deleteDialogFragment
import com.example.making_a_habit.viewmodel.DetailhabitViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailHabitActivity : AppCompatActivity() {

    val detailhabitViewModel: DetailhabitViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: DetailsHabitPageBinding
    private lateinit var habit3round: Habit3RoundAdapter

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var habit : Habit
        var habitDateStart : String

        /***** veiwBinding *****/
        binding = DetailsHabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnDetailshabitpage.setOnClickListener{
            finish()
        }
        binding.deleteBtnDetailshabitpage.setOnClickListener{

        }

        // MainRecyclerViewAdapter에서 해당 item 데이터 habitId 받음
        if(intent.hasExtra("data")) {
            // intent를 통해 클릭한 item habitId를 가져옴
            val habitId = intent.getIntExtra("data",0)
            println(habitId) // 잘 나오는지 확인
            CoroutineScope(Dispatchers.IO).launch {
                println(detailhabitViewModel.loadAllByIds(intent.getIntExtra("data", 0)))
                habit = detailhabitViewModel.loadAllByIds(intent.getIntExtra("data", 0))
                println(habit.habitName)

                runOnUiThread {
                    habitDateStart = habit.habitDateStart
                    binding.habitNameTextDetailshabitpage.text = habit.habitName
                    when(habit.habitColor){
                        "red" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFAEAE"))
                        "yellow" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFE8AE"))
                        "green" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#B1CFD1"))
                        "blue" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AED8FF"))
                        "gray" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#CECECE"))
                    } // TODO Color 지정해준 거 나중에 변경하기 (R.color.theme_?)이 안되는지 모르겠음
                    binding.habitDateStartTextDetailshabitpage.text = habitDateStart
                }
            }
        }


        /***** dialog 부분 *****/
        binding.deleteBtnDetailshabitpage.setOnClickListener{
            var deletedialog = deleteDialogFragment()
            deletedialog.show(supportFragmentManager, "deleteDialog")
        }

        /***** ViewModel 부분 *****/
//        habitViewModel.getAll().observe(this, Observer<List<Habit>>{ habits ->
//            // update UI
//            //adapter.
//        })

    }  // onCreate
}
package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.making_a_habit.databinding.DetailsHabitPageBinding
import com.example.making_a_habit.databinding.ItemRoundfullBinding
import com.example.making_a_habit.model.DetailItem
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.adapter.DetailHabitAdapter
import com.example.making_a_habit.view.dialog.CompleteDialogFragment
import com.example.making_a_habit.view.dialog.deleteDialogFragment
import com.example.making_a_habit.viewmodel.DetailhabitViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailHabitActivity : AppCompatActivity()  {

    /***** ViewModel *****/
    val detailhabitViewModel: DetailhabitViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: DetailsHabitPageBinding
    private lateinit var bindingItem : ItemRoundfullBinding
    /***** Adapter ****/
    private val detailHabitAdapter: DetailHabitAdapter = DetailHabitAdapter { habit ->
        // put extras of contact info & start CreatingHabitActivity
    }

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


        // MainRecyclerViewAdapter에서 해당 item 데이터 habitId 받음
        if(intent.hasExtra("data")) {
            // intent를 통해 클릭한 item habitId를 가져옴
            CoroutineScope(Dispatchers.IO).launch {
                println(detailhabitViewModel.getHabitId(intent.getIntExtra("data", 0)))
                habit = detailhabitViewModel.getHabitId(intent.getIntExtra("data", 0))
                println(habit.habitName)

                runOnUiThread {
                    binding.habitNameTextDetailshabitpage.text = habit.habitName
                    when(habit.habitColor){
                        "red" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFAEAE"))
                        "yellow" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFE8AE"))
                        "green" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#B1CFD1"))
                        "blue" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AED8FF"))
                        "gray" -> binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#CECECE"))
                    } // TODO Color 지정해준 거 나중에 변경하기 (R.color.theme_?)이 안되는지 모르겠음


                    if(habit.habitPeriod == "기간"){
                        println("기간이 넘어옴!")
                        habitDateStart = habit.habitDateStart + " ~ " + habit.habitDateEnd
                        binding.habitDateTextDetailshabitpage.text = habitDateStart
                    }
                    else if(habit.habitPeriod == "횟수"){
                        println("횟수가 넘어옴!")
                        habitDateStart = habit.habitDateStart + " ~"
                        binding.habitDateTextDetailshabitpage.text = habitDateStart
                    }


                    if(habit.habitPeriodNum == 3) {
                        /***** Adapter 연결 + gridLayout *****/
                        binding.detailshabitpageRecyclerView.adapter = detailHabitAdapter
                        binding.detailshabitpageRecyclerView.layoutManager = GridLayoutManager(this@DetailHabitActivity, 3)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        detailHabitAdapter.sethabitPeriod(DetailItem(habit.habitPeriodNum, habit.habitColor, habit.habitPeriod, habit.habitLastRoundFull))
                    }
                    else if(habit.habitPeriodNum == 15){
                        /***** Adapter 연결 + gridLayout *****/
                        binding.detailshabitpageRecyclerView.adapter = detailHabitAdapter
                        binding.detailshabitpageRecyclerView.layoutManager = GridLayoutManager(this@DetailHabitActivity, 5)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        detailHabitAdapter.sethabitPeriod(DetailItem(habit.habitPeriodNum, habit.habitColor, habit.habitPeriod, habit.habitLastRoundFull))
                    }
                    else if(habit.habitPeriodNum == 30){
                        /***** Adapter 연결 + gridLayout *****/
                        binding.detailshabitpageRecyclerView.adapter = detailHabitAdapter
                        binding.detailshabitpageRecyclerView.layoutManager = GridLayoutManager(this@DetailHabitActivity, 5)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        detailHabitAdapter.sethabitPeriod(DetailItem(habit.habitPeriodNum, habit.habitColor, habit.habitPeriod, habit.habitLastRoundFull))
                    }

                }
            }
        }


        /***** dialog 부분 *****/
        binding.deleteBtnDetailshabitpage.setOnClickListener{
            var deletedialog = deleteDialogFragment()

            /*** Dialog에 해당 item habitId 보내기 ***/
            val habitId = intent.getIntExtra("data",0)
            println("habitId : " + habitId)
            val bundle = Bundle()
            bundle.putInt("deleteId", habitId)
            deletedialog.arguments = bundle
            println("보냄")

            deletedialog.show(supportFragmentManager, "deleteDialog")
        }


        /* TODO 임시!!!!!! 나중에 꼭 삭제하기!!!!!!!!!! */
        /***** comment_page로 가는 button (임시) *****/
        binding.testButton.setOnClickListener{
            var completedialog = CompleteDialogFragment()

            /*** Dialog에 해당 item habitId 보내기 ***/
            val habitId = intent.getIntExtra("data",0)
            println("habitId : " + habitId)
            val bundle = Bundle()
            bundle.putInt("completeId", habitId)
            completedialog.arguments = bundle
            println("보냄")
            /*
            CoroutineScope(Dispatchers.IO).launch{
                habit = detailhabitViewModel.loadAllByIds(intent.getIntExtra("data",0))
                detailhabitViewModel.update(Habit(habitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, habit.habitDateEnd, habit.habitRoundFull, true, habit.habitComment))
            }
             */
            completedialog.show(supportFragmentManager, "deleteDialog")
        }

    }  // onCreate
}
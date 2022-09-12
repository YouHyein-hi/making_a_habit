package com.example.making_a_habit.view

import android.annotation.SuppressLint
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
    private val detailHabitAdapter: DetailHabitAdapter = DetailHabitAdapter(
        { habit ->
        }, getAdapterData()
    )

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var habit : Habit
        var habitDateStart : String
        var getAdapterData = getAdapterData()

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
                    }


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
                        //detailHabitAdapter = DetailHabitAdapter(getAdapterData)
                        binding.detailshabitpageRecyclerView.adapter = detailHabitAdapter
                        binding.detailshabitpageRecyclerView.layoutManager = GridLayoutManager(this@DetailHabitActivity, 3)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        detailHabitAdapter.sethabitPeriod(DetailItem(habit.habitPeriodNum, habit.habitColor, habit.habitPeriod,habit.habitDateIng, habit.habitDateEnd, habit.habitRoundFull, habit.habitLastRoundFull))
                    }
                    else if(habit.habitPeriodNum == 15){
                        /***** Adapter 연결 + gridLayout *****/
                        binding.detailshabitpageRecyclerView.adapter = detailHabitAdapter
                        binding.detailshabitpageRecyclerView.layoutManager = GridLayoutManager(this@DetailHabitActivity, 5)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        detailHabitAdapter.sethabitPeriod(DetailItem(habit.habitPeriodNum, habit.habitColor, habit.habitPeriod,habit.habitDateIng, habit.habitDateEnd, habit.habitRoundFull, habit.habitLastRoundFull))
                    }
                    else if(habit.habitPeriodNum == 30){
                        /***** Adapter 연결 + gridLayout *****/
                        binding.detailshabitpageRecyclerView.adapter = detailHabitAdapter
                        binding.detailshabitpageRecyclerView.layoutManager = GridLayoutManager(this@DetailHabitActivity, 5)
                        binding.detailshabitpageRecyclerView.setHasFixedSize(true)
                        detailHabitAdapter.sethabitPeriod(DetailItem(habit.habitPeriodNum, habit.habitColor, habit.habitPeriod,habit.habitDateIng, habit.habitDateEnd, habit.habitRoundFull, habit.habitLastRoundFull))
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

    }  // onCreate

    inner class getAdapterData {
        fun getData(dateIng: String, roundfull: Int, lastround: Int){
            println("getAdapterData 실행함 :::  dataInt(" + dateIng + "), roundfull(" + roundfull + "), lastround(" + lastround + ")" )

            var habit : Habit
            if(intent.hasExtra("data")) {
                // intent를 통해 클릭한 item habitId를 가져옴
                CoroutineScope(Dispatchers.IO).launch {
                    println(detailhabitViewModel.getHabitId(intent.getIntExtra("data", 0)))
                    habit = detailhabitViewModel.getHabitId(intent.getIntExtra("data", 0))

                    /** DetailHabitAdapter로 부터 dateIng, roundfull, lastround 받아서 Update 시켜줌 **/
                    detailhabitViewModel.update(Habit(habit.habitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, dateIng, habit.habitDateEnd, roundfull, lastround, habit.habitComplete, habit.habitComment))
                }
            }
        }


        /***** 완료 이벤트 *****/
        fun completeDialog(){
            println("completeDialog가 실행됨")
            var habit : Habit
            if(intent.hasExtra("data")){
                println("completeDialog : hasExtra 잘 됨")
                CoroutineScope(Dispatchers.IO).launch {
                    habit = detailhabitViewModel.getHabitId(intent.getIntExtra("data", 0))

                    if(habit.habitPeriod == "횟수"){
                        println("completeDialog : 횟수인 거 잘 인식 함")
                        if(habit.habitPeriodNum == 3)
                            showCompleteDialog()
                        else if(habit.habitPeriodNum == 15)
                            showCompleteDialog()
                        else if(habit.habitPeriodNum == 30)
                            showCompleteDialog()
                    }
                    if(habit.habitPeriod == "기간") showCompleteDialog()
                }
            }
        }

        /***** 완료 Dialog 띄우기 *****/
        fun showCompleteDialog(){
            var completedialog = CompleteDialogFragment()

            /*** Dialog에 해당 item habitId 보내기 ***/
            val habitId = intent.getIntExtra("data",0)
            println("habitId : " + habitId)
            val bundle = Bundle()
            bundle.putInt("completeId", habitId)
            completedialog.arguments = bundle
            println("보냄")
            completedialog.show(supportFragmentManager, "deleteDialog")
        }
    }


}
package com.example.making_a_habit.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.databinding.DetailsDonehabitPageBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.view.dialog.deleteDialogFragment
import com.example.making_a_habit.viewmodel.DetailDonehabitViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailDoneHabitActivity : AppCompatActivity() {

    /***** ViewModel *****/
    val detaildonehabitViewModel: DetailDonehabitViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: DetailsDonehabitPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var habit : Habit

        /***** veiwBinding *****/
        binding = DetailsDonehabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnDetailsdonehabitpage.setOnClickListener {
            finish()
        }


        /***** 데이터 받고 해당 화면에 출력하기 *****/
        if(intent.hasExtra("data")) {
            // intent를 통해 클릭한 item habitId를 가져옴
            val habitId = intent.getIntExtra("data",0)
            println(habitId) // 잘 나오는지 확인
            CoroutineScope(Dispatchers.IO).launch {
                println(detaildonehabitViewModel.loadAllByIds(intent.getIntExtra("data", 0)))
                habit = detaildonehabitViewModel.loadAllByIds(intent.getIntExtra("data", 0))
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
                    val habitDate = habit.habitDateStart + " ~ " + habit.habitDateEnd
                    binding.habitDateTextDetailshabitpage.text = habitDate
                    binding.habitCommentTextDetailshabitpage.text = habit.habitComment
                }
            }
        }

        /***** dialog 부분 *****/
        binding.deleteBtnDetailsdonehabitpage.setOnClickListener{
            var deletedialog = deleteDialogFragment()

            /*** Dialog에 해당 item habitId 보내기 ***/
            val habitId = intent.getIntExtra("data",0)
            val bundle = Bundle()
            bundle.putInt("deleteId", habitId)
            deletedialog.arguments = bundle

            deletedialog.show(supportFragmentManager, "deleteDialog")
        }

    } // OnCreat

}
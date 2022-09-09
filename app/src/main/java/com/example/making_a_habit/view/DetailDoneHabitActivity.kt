package com.example.making_a_habit.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.R
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
                println(detaildonehabitViewModel.getHabitId(intent.getIntExtra("data", 0)))
                habit = detaildonehabitViewModel.getHabitId(intent.getIntExtra("data", 0))
                println(habit.habitName)

                runOnUiThread {
                    binding.habitNameTextDetailshabitpage.text = habit.habitName  // habitName
                    val habitDate = habit.habitDateStart + " ~ " + habit.habitDateEnd
                    binding.habitDateTextDetailshabitpage.text = habitDate  // habitDateStart ~ habitDateEnd
                    when(habit.habitColor){  // habitColor theme 설정
                        "red" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFAEAE"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_red)
                        }
                        "yellow" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#FFE8AE"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_yellow)
                        }
                        "green" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#B1CFD1"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_green)
                        }
                        "blue" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#AED8FF"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_blue)
                        }
                        "gray" -> {
                            binding.habitNameTextDetailshabitpage.setTextColor(Color.parseColor("#CECECE"))
                            binding.roundfullTextDetailshabitpage.setBackgroundResource(R.drawable.textbox_theme_gray)
                        }
                    }
                    val roundfull = habit.habitRoundFull.toString() + " / " + habit.habitPeriodNum.toString()
                    binding.roundfullTextDetailshabitpage.text = roundfull  // habitRoundFull / habitPeriodNum
                    binding.habitCommentTextDetailshabitpage.text = habit.habitComment  // habitComment
                    if(habit.habitComment == "이 습관은 커멘트가 작성되지 않았습니다."){  // habitComment 작성 안할 시 내용 보여주고 text 색 변경
                        binding.habitCommentTextDetailshabitpage.setTextColor(Color.parseColor("#C9C9C9"))
                    }
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
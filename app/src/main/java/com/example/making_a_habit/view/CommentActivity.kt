package com.example.making_a_habit.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.making_a_habit.databinding.CommentPageBinding
import com.example.making_a_habit.databinding.DetailsHabitPageBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.CommentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentActivity : AppCompatActivity() {

    /***** ViewModel *****/
    val commentViewModel: CommentViewModel by viewModels()
    /***** veiwBinding *****/
    private lateinit var binding: CommentPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var habit : Habit

        /***** veiwBinding *****/
        binding = CommentPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnCreatingcomentspage.setOnClickListener {
            finish()
        }
        binding.cancelBtnCreatingcomentspage.setOnClickListener {
            finish()
        }

        if(intent.hasExtra("commentId")) {
            // intent를 통해 클릭한 item habitId를 가져옴
            CoroutineScope(Dispatchers.IO).launch {
                habit = commentViewModel.getHabitId(intent.getIntExtra("commentId", 0))
                runOnUiThread{
                    binding.habitNameTextCreatingcomentspage.text = habit.habitName
                    when(habit.habitColor){
                        "red" -> binding.habitNameTextCreatingcomentspage.setTextColor(Color.parseColor("#FFAEAE"))
                        "yellow" -> binding.habitNameTextCreatingcomentspage.setTextColor(Color.parseColor("#FFE8AE"))
                        "green" -> binding.habitNameTextCreatingcomentspage.setTextColor(Color.parseColor("#B1E6E6"))
                        "blue" -> binding.habitNameTextCreatingcomentspage.setTextColor(Color.parseColor("#AED8FF"))
                        "gray" -> binding.habitNameTextCreatingcomentspage.setTextColor(Color.parseColor("#CECECE"))
                    }
                }
            }
        }



        /***** editText 20자 초과시 Toast 띄우기 *****/
        binding.habitComentEdittextCreatingcomentspage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {
            } // 텍스트가 변경된 이후에 동작

            override fun onTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {
            } // 텍스트가 변경되기 바로 이전에 동작

            override fun afterTextChanged(h: Editable?) {
                if(h != null && !h.toString().equals("")){
                    if(h.toString().length == 100){
                        val habitName20Up = "커멘트는 100자까지만 쓸 수 있습니다!"
                        Toast.makeText(applicationContext, habitName20Up, Toast.LENGTH_SHORT).show()
                    }
                }
            } // 텍스트가 변경되는 동시에 동작
        })


        /***** '완료하기' button 클릭시 habitComplete 데이터 수정 *****/
        binding.creatingBtnCreatingcomentspage.setOnClickListener{
            val comment = binding.habitComentEdittextCreatingcomentspage.text.toString()
            println("완료 버튼 누름")
            // DetailHabitActivity에서 해당 item 데이터 habitId 받음
            if(intent.hasExtra("commentId")) {
                // intent를 통해 클릭한 item habitId를 가져옴
                CoroutineScope(Dispatchers.IO).launch{
                    val habitId = intent.getIntExtra("commentId",0)
                    habit = commentViewModel.getHabitId(intent.getIntExtra("commentId", 0))
                    println("habitId : " + habitId)
                    println("habit : " + habit)
                    commentViewModel.update(Habit(habitId, habit.habitName, habit.habitPeriod, habit.habitPeriodNum, habit.habitColor, habit.habitDateStart, habit.habitDateIng ,habit.habitDateEnd, habit.habitRoundFull, habit.habitLastRoundFull, habit.habitComplete, comment))
                }
            }
            else println("없음")
        finish()
        }
    }
}
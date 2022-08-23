package com.example.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.databinding.CreatingCommentsBinding
import com.example.making_a_habit.databinding.CreatingHabitPageBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.HabitViewModel
import java.time.LocalDate

class CreatinghabitActivity : AppCompatActivity() {

    val habitViewModel: HabitViewModel by viewModels()
    private var id: Long? = null

    /***** veiwBinding *****/
    private lateinit var binding: CreatingHabitPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //habitViewModel = ViewModelProvider.get(HabitViewModel::class.java)
        /***** veiwBinding *****/
        binding = CreatingHabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var habitPeriod: String? = null


        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnCreatinghabitpage.setOnClickListener{
            finish()
        }


        /***** habitPeriod_btn 클릭하면 버튼눌림효과 유지 *****/
        // 기간 눌림효과, 눌림효과 해제  횟수 눌림효과, 눌림효과 해제
        // 버튼 누른 상태에서 다른 버튼 누르면 눌림효과 해제 후 눌림

        // 기간 Button
        binding.habitPeriodBtnTime.setOnClickListener{
            if(binding.habitPeriodBtnTime.isSelected()){  // 버튼이 눌려있을 경우 (selected 상태)
                binding.habitPeriodBtnTime.isSelected = false
            } else { // 버튼이 안 눌려있을 경우 (selected 상태)
                binding.habitPeriodBtnTime.isSelected = true
                binding.habitPeriodBtnNumber.isSelected = false  // 다른 버튼이 눌린 상태에서 눌렸을 경우를 대비
            }
            habitPeriod = "기간"
        }
        // 횟수 Button
        binding.habitPeriodBtnNumber.setOnClickListener{
            if(binding.habitPeriodBtnNumber.isSelected()){  // 버튼이 눌려있을 경우 (selected 상태)
                binding.habitPeriodBtnNumber.isSelected = false
            } else { // 버튼이 안 눌려있을 경우 (selected 상태)
                binding.habitPeriodBtnNumber.isSelected = true
                binding.habitPeriodBtnTime.isSelected = false  // 다른 버튼이 눌린 상태에서 눌렸을 경우를 대비
            }
            habitPeriod = "횟수"
        }


        // 3칸 Button


        /***** '생성하기'버튼 클릭시 DB 저장 및 화면 전환 *****/
        binding.creatinghabitBtn.setOnClickListener{
            val habitName = binding.habitNameEdittext.text.toString()
            val habitDateStart: LocalDate = LocalDate.now()

            val initial = habitName[0].toUpperCase()
            val habit = Habit(id, habitName, habitPeriod.toString(), "임시", habitDateStart.toString(),
                    "임시", 0, false, "임시")
            println("Habit : " + habit)
            habitViewModel.insert(habit)
            finish()
        }


        /***** ViewModel 부분 *****/
        //val habitViewModel: HabitViewModel by viewModels()
        habitViewModel.getAll().observe(this, Observer<List<Habit>>{ habits ->
            // update UI
        })

    } // onCreate

    // TODO editText 토스트 메세지 띄우기
    /*
    fun abc(){
        habitName_edittext.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {
            } // 텍스트가 변경된 이후에 동작

            override fun onTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {
            } // 텍스트가 변경되기 바로 이전에 동작

            override fun afterTextChanged(h: Editable?) {

                if(h != null && !h.toString().equals("")){
                    //if(h.toString().toByte().length <= 20)
                }

            } // 텍스트가 변경되는 동시에 동작
        })
    }
     */
}
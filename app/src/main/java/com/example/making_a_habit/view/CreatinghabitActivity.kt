package com.example.making_a_habit.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.making_a_habit.databinding.CreatingHabitPageBinding
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.HabitViewModel
import java.time.LocalDate

class CreatinghabitActivity : AppCompatActivity() {

    val habitViewModel: HabitViewModel by viewModels()
    private var id: Int? = null

    /***** veiwBinding *****/
    private lateinit var binding: CreatingHabitPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //habitViewModel = ViewModelProvider.get(HabitViewModel::class.java)
        /***** veiwBinding *****/
        binding = CreatingHabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var habitPeriod: String? = null
        var hebitPeriodNum: Int? = 0
        var habitColor: String? = null


        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnCreatinghabitpage.setOnClickListener{
            finish()
        }


        /***** editText 20자 초과시 Toast 띄우기 *****/
        // ToDo Toast 메시지 디자인 하기
        binding.habitNameEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {
            } // 텍스트가 변경된 이후에 동작

            override fun onTextChanged(h: CharSequence?, p1: Int, p2: Int, p3: Int) {
            } // 텍스트가 변경되기 바로 이전에 동작

            override fun afterTextChanged(h: Editable?) {
                if(h != null && !h.toString().equals("")){
                    if(h.toString().length == 20){
                        val habitName20Up = "습관 이름은 20자까지만 가능합니다!"
                        Toast.makeText(applicationContext, habitName20Up, Toast.LENGTH_SHORT).show()
                    }
                }
            } // 텍스트가 변경되는 동시에 동작
        })


        /***** habitPeriod_btn 클릭하면 버튼눌림효과 유지 *****/
        // 기간 눌림효과, 눌림효과 해제  횟수 눌림효과, 눌림효과 해제
        // 버튼 누른 상태에서 다른 버튼 누르면 눌림효과 해제 후 눌림
        // TODO 여기 있는 모든 코드들이 구림. 정말 이게 최선...?

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
        binding.habitPeriodNumBtn3.setOnClickListener{
            if(binding.habitPeriodNumBtn3.isSelected()){
                binding.habitPeriodNumBtn3.isSelected = false
            } else {
                binding.habitPeriodNumBtn3.isSelected = true

                binding.habitPeriodNumBtn15.isSelected = false
                binding.habitPeriodNumBtn30.isSelected = false
            }
            hebitPeriodNum = 3
        }
        // 15칸 button
        binding.habitPeriodNumBtn15.setOnClickListener{
            if(binding.habitPeriodNumBtn15.isSelected()){
                binding.habitPeriodNumBtn15.isSelected = false
            } else {
                binding.habitPeriodNumBtn15.isSelected = true

                binding.habitPeriodNumBtn3.isSelected = false
                binding.habitPeriodNumBtn30.isSelected = false
            }
            hebitPeriodNum = 15
        }
        // 30칸 button
        binding.habitPeriodNumBtn30.setOnClickListener{
            if(binding.habitPeriodNumBtn30.isSelected()){
                binding.habitPeriodNumBtn30.isSelected = false
            } else {
                binding.habitPeriodNumBtn30.isSelected = true

                binding.habitPeriodNumBtn3.isSelected = false
                binding.habitPeriodNumBtn15.isSelected = false
            }
            hebitPeriodNum = 30
        }


        // theme red button
        binding.themeRed.setOnClickListener{
            if(binding.themeRed.isSelected()){
                binding.themeRed.isSelected = false
                println("버튼 누름효과 제거")
            } else {
                binding.themeRed.isSelected = true
                println("버튼 누름효과")
                binding.themeYellow.isSelected = false
                binding.themeGreen.isSelected = false
                binding.themeBlue.isSelected = false
                binding.themeGray.isSelected = false
            }
            habitColor = "red"
        }
        // theme yellow button
        binding.themeYellow.setOnClickListener{
            if(binding.themeYellow.isSelected()){
                binding.themeYellow.isSelected = false
                println("버튼 누름효과 제거")
            } else {
                binding.themeYellow.isSelected = true
                println("버튼 누름효과")
                binding.themeRed.isSelected = false
                binding.themeGreen.isSelected = false
                binding.themeBlue.isSelected = false
                binding.themeGray.isSelected = false
            }
            habitColor = "yellow"
        }
        // theme green button
        binding.themeGreen.setOnClickListener{
            if(binding.themeGreen.isSelected()){
                binding.themeGreen.isSelected = false
                println("버튼 누름효과 제거")
            } else {
                binding.themeGreen.isSelected = true
                println("버튼 누름효과")
                binding.themeRed.isSelected = false
                binding.themeYellow.isSelected = false
                binding.themeBlue.isSelected = false
                binding.themeGray.isSelected = false
            }
            habitColor = "green"
        }
        // theme blue button
        binding.themeBlue.setOnClickListener{
            if(binding.themeBlue.isSelected()){
                binding.themeBlue.isSelected = false
                println("버튼 누름효과 제거")
            } else {
                binding.themeBlue.isSelected = true
                println("버튼 누름효과")
                binding.themeRed.isSelected = false
                binding.themeYellow.isSelected = false
                binding.themeGreen.isSelected = false
                binding.themeGray.isSelected = false
            }
            habitColor = "blue"
        }
        // theme gray button
        binding.themeGray.setOnClickListener{
            if(binding.themeGray.isSelected()){
                binding.themeGray.isSelected = false
                println("버튼 누름효과 제거")
            } else {
                binding.themeGray.isSelected = true
                println("버튼 누름효과")
                binding.themeRed.isSelected = false
                binding.themeYellow.isSelected = false
                binding.themeGreen.isSelected = false
                binding.themeBlue.isSelected = false
            }
            habitColor = "gray"
        }


        /***** '생성하기'버튼 클릭시 DB 저장 및 화면 전환 *****/
        binding.creatinghabitBtn.setOnClickListener{
            val habitName = binding.habitNameEdittext.text.toString()
            val habitDateStart: LocalDate = LocalDate.now()



            // and &&   or ||
            // TODO 조건이 너무 구려 어떻게 짧게 할 수 없나?
            // TODO 그리고 EditText 부분은 안적어도 그냥 넘어감 해결하자!
            // binding.habitNameEdittext.toString() != null &&
            if(
                (binding.habitPeriodBtnTime.isSelected || binding.habitPeriodBtnNumber.isSelected) &&
                (binding.habitPeriodNumBtn3.isSelected || binding.habitPeriodNumBtn15.isSelected || binding.habitPeriodNumBtn30.isSelected) &&
                (binding.themeRed.isSelected || binding.themeYellow.isSelected || binding.themeGreen.isSelected || binding.themeBlue.isSelected || binding.themeGray.isSelected)
            ){
                val initial = habitName[0].toUpperCase()
                val habit = Habit(id, habitName, habitPeriod.toString(), hebitPeriodNum,habitColor.toString(), habitDateStart.toString(),
                    "임시", 0, false, "임시")
                println("Habit : " + habit)

                habitViewModel.insert(habit)
                println("다 선택함")
                finish()
            }
            else{
                val creatinghabitbutton = "모든 옵션을 선택해주세요."
                println(creatinghabitbutton)
                Toast.makeText(applicationContext, creatinghabitbutton, Toast.LENGTH_SHORT).show()

            }


        }


        /***** ViewModel 부분 *****/
        //val habitViewModel: HabitViewModel by viewModels()
        habitViewModel.getAll().observe(this, Observer<List<Habit>>{ habits ->
            // update UI
        })

    } // onCreate

}
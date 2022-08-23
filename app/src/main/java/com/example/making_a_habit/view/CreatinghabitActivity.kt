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

        var back_btn = findViewById<ImageView>(R.id.back_btn_creatinghabitpage)
        var creatingHabit_btn = findViewById<Button>(R.id.creatinghabit_btn)
        var habitName_edittext = findViewById<EditText>(R.id.habitName_edittext)

        /***** 페이지 간 화면 전환 (뒤로가기)*****/
        binding.backBtnCreatinghabitpage.setOnClickListener{
            finish()
        }

        /***** '생성하기'버튼 클릭시 DB 저장 및 화면 전환 *****/
        creatingHabit_btn.setOnClickListener{
            val habitName = habitName_edittext.text.toString()
            val habitDateStart: LocalDate = LocalDate.now()

            val initial = habitName[0].toUpperCase()
            val habit = Habit(id, habitName, "임시", "임시", habitDateStart.toString(),
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
package com.example.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.making_a_habit.R
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.viewmodel.HabitViewModel
import java.time.LocalDate

class CreatingHabitActivity : AppCompatActivity() {

    val habitViewModel: HabitViewModel by viewModels()
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creating_habit_page)

        val back_btn = findViewById<ImageView>(R.id.back_btn_creatinghabitpage)
        val creatingHabit_btn = findViewById<Button>(R.id.creatinghabit_btn)

        back_btn.setOnClickListener{
            finish()
        }
        creatingHabit_btn.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val habitName_edittext = findViewById<EditText>(R.id.habitName_edittext)

        creatingHabit_btn.setOnClickListener{
            val habitName = habitName_edittext.text.toString()
            val habitDateStart: LocalDate = LocalDate.now()

            val initial = habitName[0].toUpperCase()
            val habit = Habit(id, habitName, "임시", "임시", habitDateStart.toString(), "임시", 0, false, "임시")
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

}
package com.example.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.R

class CreatingHabitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creating_habit_page)

        val back_btn = findViewById<ImageView>(R.id.back_btn_creatinghabitpage)
        val creatingHabit_btn = findViewById<Button>(R.id.creatinghabit_btn)

        back_btn.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        creatingHabit_btn.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}
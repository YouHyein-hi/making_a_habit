package com.example.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ActivityMainBinding
import com.example.making_a_habit.databinding.CreatingHabitPageBinding
import com.example.making_a_habit.databinding.ListDonehabitPageBinding

class ClockActivity : AppCompatActivity() {

    /***** veiwBinding *****/
    private lateinit var binding: ListDonehabitPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***** veiwBinding *****/
        binding = ListDonehabitPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtnListdonehabitpage.setOnClickListener{
            finish()
        }
    }

}
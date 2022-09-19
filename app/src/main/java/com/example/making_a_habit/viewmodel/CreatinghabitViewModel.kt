package com.example.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.making_a_habit.HabitRepository
import com.example.making_a_habit.model.Habit

class CreatinghabitViewModel(application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)

    fun insert(habit: Habit) {
        repository.insert(habit)
    }
}
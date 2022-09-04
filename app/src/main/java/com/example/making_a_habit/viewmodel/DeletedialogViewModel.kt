package com.example.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.making_a_habit.HabitRepository
import com.example.making_a_habit.model.Habit

class DeletedialogViewModel (application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)

    fun delete(habit: Habit) {
        repository.delete(habit)
    }

    /*suspend fun deleteIds(habitIds: Int){
        repository.deleteIds(habitIds)
    }*/
}
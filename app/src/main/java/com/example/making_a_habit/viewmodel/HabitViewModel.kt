package com.example.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.making_a_habit.HabitRepository
import com.example.making_a_habit.model.Habit

class HabitViewModel (application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)
    private val habits = repository.getAll()

    fun getAll(): LiveData<List<Habit>> {
        return habits
    }

    fun insert(habit: Habit) {
        repository.insert(habit)
    }

    fun delete(habit: Habit) {
        repository.delete(habit)
    }

    fun getHabitId(habitId: Long){
        repository.getHabitId(habitId)
    }
}
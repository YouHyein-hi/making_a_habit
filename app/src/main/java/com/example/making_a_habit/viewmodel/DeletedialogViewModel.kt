package com.example.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.making_a_habit.HabitRepository
import com.example.making_a_habit.model.Habit
import kotlinx.coroutines.withContext

class DeletedialogViewModel (application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)

    suspend fun getHabitId(habitId: Int): Habit {
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }

    fun delete(habit: Habit) {
        repository.delete(habit)
    }
}
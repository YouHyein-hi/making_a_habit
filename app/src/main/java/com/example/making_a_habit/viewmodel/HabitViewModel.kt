package com.example.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.making_a_habit.HabitRepository
import com.example.making_a_habit.model.Habit
import kotlinx.coroutines.withContext

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

    suspend fun loadAllByIds(habitId: Int): Habit {
        return withContext(viewModelScope.coroutineContext) {
            repository.loadAllByIds(habitId)
        }

    }


    suspend fun getHabitId(habitId: Int): Habit{
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }
}
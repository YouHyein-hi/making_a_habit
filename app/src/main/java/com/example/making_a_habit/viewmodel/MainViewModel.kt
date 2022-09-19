package com.example.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.making_a_habit.HabitRepository
import com.example.making_a_habit.model.Habit
import kotlinx.coroutines.withContext

class MainViewModel (application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)

    suspend fun getAll(): List<Habit> {
        return withContext(viewModelScope.coroutineContext) {
            repository.getAll()
        }
    }
}
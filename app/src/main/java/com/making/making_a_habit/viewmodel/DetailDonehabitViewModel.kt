package com.making.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.making.making_a_habit.HabitRepository
import com.making.making_a_habit.model.Habit
import kotlinx.coroutines.withContext

class DetailDonehabitViewModel (application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)

    suspend fun getHabitId(habitId: Int): Habit {
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }
}
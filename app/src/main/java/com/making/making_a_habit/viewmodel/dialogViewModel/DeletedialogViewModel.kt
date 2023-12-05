package com.making.making_a_habit.viewmodel.dialogViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.making.making_a_habit.repository.HabitRepository
import com.making.making_a_habit.room.Habit
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
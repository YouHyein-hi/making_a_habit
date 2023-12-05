package com.making.making_a_habit.viewmodel.dialogViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.making.making_a_habit.repository.HabitRepository
import com.example.data.entity.HabitEntity
import kotlinx.coroutines.withContext

class CompleteDialogViewModel (application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)

    suspend fun getHabitId(habitId: Int): HabitEntity {
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }

    suspend fun update(habit: HabitEntity) {
        return withContext(viewModelScope.coroutineContext) {
            repository.update(habit)
        }
    }
}
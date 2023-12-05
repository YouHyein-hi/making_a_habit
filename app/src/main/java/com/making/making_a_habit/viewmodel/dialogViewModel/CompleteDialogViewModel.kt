package com.making.making_a_habit.viewmodel.dialogViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entity.HabitEntity
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import kotlinx.coroutines.withContext

class CompleteDialogViewModel(private val repository : HabitRepository) : ViewModel(){

    /*suspend fun getHabitId(habitId: Int): HabitEntity {
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }*/

    suspend fun update(habit: HabitData) {
        return withContext(viewModelScope.coroutineContext) {
            repository.update(habit)
        }
    }
}
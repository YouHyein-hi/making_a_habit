package com.making.making_a_habit.viewmodel.dialogViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entity.HabitEntity
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeletedialogViewModel(private val repository : HabitRepository) : ViewModel(){

   /* suspend fun getHabitId(habitId: Int): HabitEntity {
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }*/

    fun delete(habit: HabitData) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.delete(habit)
        }
    }
}
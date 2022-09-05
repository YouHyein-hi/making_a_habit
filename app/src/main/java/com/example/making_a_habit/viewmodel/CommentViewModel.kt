package com.example.making_a_habit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.making_a_habit.HabitRepository
import com.example.making_a_habit.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentViewModel (application: Application) : AndroidViewModel(application){

    private val repository = HabitRepository(application)

    suspend fun loadAllByIds(habitId: Int): Habit {
        return withContext(viewModelScope.coroutineContext) {
            repository.loadAllByIds(habitId)
        }
    }

    fun update(habit: Habit){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(habit)
        }
    }

}
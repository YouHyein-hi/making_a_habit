package com.making.making_a_habit.viewmodel.fragmentViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.data.entity.HabitEntity
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(private val repository : HabitRepository) : ViewModel(){

    fun updateData(habit: HabitData){
        CoroutineScope(Dispatchers.IO).launch {
            repository.update(habit)
        }
    }
}
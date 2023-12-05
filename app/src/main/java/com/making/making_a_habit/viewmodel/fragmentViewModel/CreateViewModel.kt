package com.making.making_a_habit.viewmodel.fragmentViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.making.making_a_habit.repository.HabitRepository
import com.example.data.entity.HabitEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HabitRepository(application)

    fun insertData(data : HabitEntity){
        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(habit = data)
        }
    }

}
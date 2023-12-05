package com.making.making_a_habit.viewmodel.fragmentViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.making.making_a_habit.repository.HabitRepository
import com.making.making_a_habit.room.Habit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HabitRepository(application)

    private var _habitData = MutableLiveData<MutableList<Habit>>()
    val habitData: LiveData<MutableList<Habit>> get() = _habitData

    fun getAllData(){
        CoroutineScope(Dispatchers.IO).launch {
            _habitData.postValue(repository.getAll())
        }
    }
}
package com.making.making_a_habit.viewmodel.fragmentViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.making.making_a_habit.repository.HabitRepository
import com.example.data.entity.HabitEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HabitRepository(application)

    private var _habitData = MutableLiveData<MutableList<HabitEntity>>()
    val habitData: LiveData<MutableList<HabitEntity>> get() = _habitData

    fun getAllData(){
        CoroutineScope(Dispatchers.IO).launch {
            _habitData.postValue(repository.getAll())
        }
    }
}
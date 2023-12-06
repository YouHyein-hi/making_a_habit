package com.making.making_a_habit.viewmodel.fragmentViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import com.example.domain.usecase.GetAllUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllUseCase: GetAllUseCase
): ViewModel(){

    private var _habitData = MutableLiveData<MutableList<HabitData>?>()
    val habitData: LiveData<MutableList<HabitData>?> get() = _habitData

    fun getAllData(){
        CoroutineScope(Dispatchers.IO).launch {
            _habitData.postValue(getAllUseCase())
        }
    }
}
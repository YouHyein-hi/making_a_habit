package com.making.making_a_habit.viewmodel.activityViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.DetailData
import com.example.domain.repository.HabitRepository

class MainViewModel : ViewModel(){

    private val _selectedData = MutableLiveData<DetailData?>()
    val selectedData : LiveData<DetailData?>
        get() = _selectedData

    fun changeSelectedData(data: DetailData?){
        _selectedData.value = data
    }



}
package com.making.making_a_habit.viewmodel.activityViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.making.making_a_habit.model.DetailData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _selectedData = MutableLiveData<DetailData?>()
    val selectedData : LiveData<DetailData?>
        get() = _selectedData

    fun changeSelectedData(data: DetailData?){
        _selectedData.value = data
    }



}
package com.making.making_a_habit.viewmodel.fragmentViewModel

import androidx.lifecycle.ViewModel
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import com.example.domain.usecase.GetAllUseCase
import com.example.domain.usecase.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val updateUseCase: UpdateUseCase
): ViewModel(){

    fun updateData(data: HabitData){
        CoroutineScope(Dispatchers.IO).launch {
            updateUseCase(data)
        }
    }

}
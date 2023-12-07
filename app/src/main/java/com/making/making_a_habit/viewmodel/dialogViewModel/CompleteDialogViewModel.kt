package com.making.making_a_habit.viewmodel.dialogViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.room.entity.HabitEntity
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import com.example.domain.usecase.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CompleteDialogViewModel@Inject constructor(
    private val updateUseCase: UpdateUseCase
): ViewModel(){

    /*suspend fun getHabitId(habitId: Int): HabitEntity {
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }*/

    suspend fun update(data: HabitData) {
        return withContext(viewModelScope.coroutineContext) {
            updateUseCase(data)
        }
    }
}
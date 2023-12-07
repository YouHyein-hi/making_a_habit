package com.making.making_a_habit.viewmodel.dialogViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.room.entity.HabitEntity
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import com.example.domain.usecase.DeleteUseCase
import com.example.domain.usecase.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DeletedialogViewModel @Inject constructor(
    private val deleteUseCase: DeleteUseCase
): ViewModel(){

   /* suspend fun getHabitId(habitId: Int): HabitEntity {
        return withContext(viewModelScope.coroutineContext) {
            repository.getHabitId(habitId)
        }
    }*/

    fun delete(data: HabitData) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteUseCase(data)
        }
    }
}
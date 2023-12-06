package com.example.domain.usecase

import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository

class UpdateUseCase (private val repository: HabitRepository) {
    suspend operator fun invoke(list: HabitData){
        repository.update(list)
    }
}
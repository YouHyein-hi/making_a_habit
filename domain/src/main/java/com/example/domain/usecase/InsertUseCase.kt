package com.example.domain.usecase

import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository

class InsertUseCase (private val repository: HabitRepository) {
    suspend operator fun invoke(list : HabitData){
        repository.insert(list)
    }
}
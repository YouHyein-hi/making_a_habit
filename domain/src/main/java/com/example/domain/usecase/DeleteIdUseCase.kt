package com.example.domain.usecase

import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository

class DeleteIdUseCase (private val repository: HabitRepository) {
    suspend operator fun invoke(id: Int): Int{
        return repository.deletetoId(id)
    }
}
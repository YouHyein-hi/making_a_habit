package com.example.domain.usecase

import android.util.Log
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository

class GetAllUseCase(private val repository: HabitRepository) {
    suspend operator fun invoke(): MutableList<HabitData>{
        return repository.getAll()
    }
}
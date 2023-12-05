package com.example.domain.repository

import com.example.domain.model.HabitData

interface HabitRepository {
    suspend fun getAll() : MutableList<HabitData>
    suspend fun insert(list : HabitData)
    suspend fun delete(list : HabitData)
    suspend fun deletetoId(id : Int) : Int
    suspend fun update(list : HabitData)
    //suspend fun getHabitId(id : Int) : HabitData
}
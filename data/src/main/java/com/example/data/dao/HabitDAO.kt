package com.example.data.dao

import androidx.room.*
import com.example.data.entity.HabitEntity

@Dao
interface HabitDAO {

    @Query("SELECT * FROM habit")
    suspend fun getAll(): MutableList<HabitEntity>

    @Insert
    fun insert(habit: HabitEntity)

    @Delete
    fun delete(habit: HabitEntity)

    @Query("Delete from habit where habitId = :id")
    fun deleteData(id : Int): Int

    @Update
    fun update(habit: HabitEntity)

    //선택한 메모리 데이터 가져오기
    @Query("SELECT * FROM habit WHERE habitId = :habitId")
    suspend fun getHabitId(habitId : Int) : HabitEntity

}
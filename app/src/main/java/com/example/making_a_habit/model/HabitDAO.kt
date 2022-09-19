package com.example.making_a_habit.model

import androidx.room.*

@Dao
interface HabitDAO {

    @Query("SELECT * FROM habit")
    suspend fun getAll(): List<Habit>

    @Insert
    fun insert(vararg habit: Habit)

    @Delete
    fun delete(habit: Habit)

    @Update
    fun update(habit: Habit)

    //선택한 메모리 데이터 가져오기
    @Query("SELECT * FROM habit WHERE habitId = :habitId")
    suspend fun getHabitId(habitId : Int) : Habit

}
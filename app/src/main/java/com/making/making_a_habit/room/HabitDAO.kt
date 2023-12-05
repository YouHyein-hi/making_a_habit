package com.making.making_a_habit.room

import androidx.room.*

@Dao
interface HabitDAO {

    @Query("SELECT * FROM habit")
    suspend fun getAll(): MutableList<Habit>

    @Insert
    fun insert(habit: Habit)

    @Delete
    fun delete(habit: Habit)

    @Query("Delete from habit where habitId = :id")
    fun deleteData(id : Int): Int

    @Update
    fun update(habit: Habit)

    //선택한 메모리 데이터 가져오기
    @Query("SELECT * FROM habit WHERE habitId = :habitId")
    suspend fun getHabitId(habitId : Int) : Habit

}
package com.example.making_a_habit.model

import androidx.lifecycle.LiveData
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

    @Query("UPDATE habit SET habitId=(:habitIds) WHERE habitLastRoundFull=(:habitLastRoundFull)")
    fun updateLastRound(habitIds: Int, habitLastRoundFull: Int)

    /*@Delete
    suspend fun deleteIds(habitIds: Int) : Habit*/





    /*
    @Query("SELECT * FROM habit WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Habit
     */

}
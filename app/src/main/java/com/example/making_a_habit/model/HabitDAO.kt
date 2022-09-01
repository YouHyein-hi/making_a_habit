package com.example.making_a_habit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HabitDAO {

    @Query("SELECT * FROM habit")
    suspend fun getAll(): List<Habit>

    @Insert
    fun insert(vararg habit: Habit)

    @Delete
    fun delete(habit: Habit)

    @Query("SELECT * FROM habit WHERE habitId IN (:habitIds)")
    fun loadAllByIds(habitIds: IntArray): LiveData<List<Habit>>

    //선택한 메모리 데이터 가져오기
    @Query("SELECT * FROM habit WHERE habitId = :habitId")
    suspend fun getHabitId(habitId : Int) : Habit

    /*
    @Query("SELECT * FROM habit WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Habit
     */

}
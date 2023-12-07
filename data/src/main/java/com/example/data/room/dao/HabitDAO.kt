package com.example.data.room.dao

import androidx.room.*
import com.example.data.room.entity.HabitEntity

@Dao
interface HabitDAO {

    @Query("SELECT * FROM habit")
    suspend fun getAll(): MutableList<HabitEntity>
    // 모든 데이터 조회

    @Insert
    fun insert(habit: HabitEntity)
    // 추가

    @Delete
    fun delete(habit: HabitEntity)
    // 삭제

    @Query("Delete from habit where habitId = :id")
    fun deleteData(id : Int): Int
    // habit id를 이용한 삭제

    @Update
    fun update(habit: HabitEntity)
    // 수정(업데이트)

    @Query("SELECT * FROM habit WHERE habitId = :habitId")
    suspend fun getHabitId(habitId : Int) : MutableList<HabitEntity>
    //선택한 메모리 데이터 가져오기

}
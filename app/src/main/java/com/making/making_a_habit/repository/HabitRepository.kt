package com.making.making_a_habit.repository

import android.app.Application
import com.example.data.entity.HabitEntity
import com.example.data.dao.HabitDAO
import com.example.data.HabitDB

class HabitRepository (application: Application){

    private val habitDatabase = HabitDB.getInstance(application)!!
    private val habitDao: HabitDAO = habitDatabase.habitDatabase()

    suspend fun getAll(): MutableList<HabitEntity> {
        return habitDao.getAll()
    }

    fun insert(habit: HabitEntity) {
        try {
            habitDao.insert(habit)
        } catch (e: Exception) { }
    }

    fun delete(habit: HabitEntity) {
        try {
            val thread = Thread {
                habitDao.delete(habit)
            }
            thread.start()
        } catch (e: Exception) { }
    }

    fun deleteData(id: Int) : Int{
        return habitDao.deleteData(id = id)
    }

    fun update(habit: HabitEntity) {
        try {
            /*
            val thread = Thread {
                habitDao.update(habit)
            }
            thread.start()
             */
            habitDao.update(habit)
        } catch (e: Exception) { }
    }

    suspend fun getHabitId(habitId: Int): HabitEntity {
        return habitDao.getHabitId(habitId)
    }
}
package com.making.making_a_habit.repository

import android.app.Application
import com.making.making_a_habit.room.Habit
import com.making.making_a_habit.room.HabitDAO
import com.making.making_a_habit.room.HabitDB

class HabitRepository (application: Application){

    private val habitDatabase = HabitDB.getInstance(application)!!
    private val habitDao: HabitDAO = habitDatabase.habitDatabase()

    suspend fun getAll(): MutableList<Habit> {
        return habitDao.getAll()
    }

    fun insert(habit: Habit) {
        try {
            habitDao.insert(habit)
        } catch (e: Exception) { }
    }

    fun delete(habit: Habit) {
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

    fun update(habit: Habit) {
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

    suspend fun getHabitId(habitId: Int): Habit {
        return habitDao.getHabitId(habitId)
    }
}
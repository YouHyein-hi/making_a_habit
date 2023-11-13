package com.making.making_a_habit

import android.app.Application
import com.making.making_a_habit.model.Habit
import com.making.making_a_habit.model.HabitDAO
import com.making.making_a_habit.model.HabitDB

class HabitRepository (application: Application){

    private val habitDatabase = HabitDB.getInstance(application)!!
    private val habitDao: HabitDAO = habitDatabase.habitDao()

    suspend fun getAll(): List<Habit> {
        return habitDao.getAll()
    }

    fun insert(habit: Habit) {
        try {
            val thread = Thread {
                habitDao.insert(habit)
            }
            thread.start()
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

    fun update(habit: Habit) {
        try {
            val thread = Thread {
                habitDao.update(habit)
            }
            thread.start()
        } catch (e: Exception) { }
    }

    suspend fun getHabitId(habitId: Int): Habit{
        return habitDao.getHabitId(habitId)
    }
}
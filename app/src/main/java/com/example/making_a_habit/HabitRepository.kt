package com.example.making_a_habit

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.making_a_habit.model.Habit
import com.example.making_a_habit.model.HabitDAO
import com.example.making_a_habit.model.HabitDB

class HabitRepository (application: Application){

    private val habitDatabase = HabitDB.getInstance(application)!!
    private val habitDao: HabitDAO = habitDatabase.habitDao()
//    private val habits: LiveData<List<Habit>> = habitDao.getAll()

    /*
    private val habitDatabase = HabitDB.getInstance(application)
    private val habitDao: HabitDAO? = habitDatabase?.habitDao()
    private val habits: LiveData<List<Habit>>? = habitDao?.getAll()     // LiveData<List<Habit>>
     */

    suspend fun getAll(): List<Habit> {
        return habitDao.getAll()
    }

    fun insert(habit: Habit) {
        try {
            val thread = Thread(Runnable {
                habitDao.insert(habit)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(habit: Habit) {
        try {
            val thread = Thread(Runnable {
                habitDao.delete(habit)
            })
            thread.start()
        } catch (e: Exception) { }
    }

    suspend fun loadAllByIds(habitId: Int): Habit{
        return habitDao.getHabitId(habitId)
    }

    suspend fun updateHabit(habit: Habit){
        return habitDao.updateHabit(habit)
    }

    /*suspend fun deleteIds(habitIds: Int) : Habit {
        return habitDao.deleteIds(habitIds)
    }*/


    suspend fun getHabitId(habitId: Int): Habit{
        return habitDao.getHabitId(habitId)
    }
}
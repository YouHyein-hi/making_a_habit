package com.example.data.repolmpl

import com.example.data.HabitDB
import com.example.data.entity.HabitEntity
import com.example.data.entity.toDomainEntity
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository

//dao : HabitDAO
class HabitRepositoryImpl(private val db : HabitDB) : HabitRepository {
    override suspend fun getAll(): MutableList<HabitData> {
        return db.habitDao().getAll().map { it.toDomainEntity() }.toMutableList()
    }

    override suspend fun insert(list: HabitData) {
        val myHabit = HabitEntity(
            habitId =list.id,
            habitName = list.name,
            habitPeriod = list.period,
            habitPeriodNum = list.periodNum,
            habitColor = list.color,
            habitDateStart = list.dateStart,
            habitDateIng = list.dateIng,
            habitDateEnd = list.dateEnd,
            habitRoundFull = list.roundFull,
            habitLastRoundFull = list.lastRoundFull,
            habitComplete = list.complete,
            habitComment = list.comment
        )
        db.habitDao().insert(myHabit)
    }

    override suspend fun delete(list: HabitData) {
        val myHabit = HabitEntity(
            habitId =list.id,
            habitName = list.name,
            habitPeriod = list.period,
            habitPeriodNum = list.periodNum,
            habitColor = list.color,
            habitDateStart = list.dateStart,
            habitDateIng = list.dateIng,
            habitDateEnd = list.dateEnd,
            habitRoundFull = list.roundFull,
            habitLastRoundFull = list.lastRoundFull,
            habitComplete = list.complete,
            habitComment = list.comment
        )
        db.habitDao().delete(myHabit)
    }

    override suspend fun deletetoId(id: Int): Int {
        return db.habitDao().deleteData(id)
    }

    override suspend fun update(list: HabitData) {
        val myHabit = HabitEntity(
            habitId =list.id,
            habitName = list.name,
            habitPeriod = list.period,
            habitPeriodNum = list.periodNum,
            habitColor = list.color,
            habitDateStart = list.dateStart,
            habitDateIng = list.dateIng,
            habitDateEnd = list.dateEnd,
            habitRoundFull = list.roundFull,
            habitLastRoundFull = list.lastRoundFull,
            habitComplete = list.complete,
            habitComment = list.comment
        )
        db.habitDao().update(myHabit)
    }

/*    override suspend fun getHabitId(id: Int): HabitData {
        return db.habitDao().getHabitId(id)
    }*/
}
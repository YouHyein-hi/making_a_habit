package com.example.data.repolmpl

import com.example.data.dao.HabitDAO
import com.example.data.entity.HabitEntity
import com.example.data.entity.toDomainEntity
import com.example.domain.model.HabitData
import com.example.domain.repository.HabitRepository
import javax.inject.Inject

//dao : HabitDAO
class HabitRepositoryImpl@Inject constructor(
    private val dao: HabitDAO
) : HabitRepository {
    override suspend fun getAll(): MutableList<HabitData> {
        return dao.getAll().map { it.toDomainEntity() }.toMutableList()
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
        dao.insert(myHabit)
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
        dao.delete(myHabit)
    }

    override suspend fun deletetoId(id: Int): Int {
        return dao.deleteData(id)
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
        dao.update(myHabit)
    }

/*    override suspend fun getHabitId(id: Int): HabitData {
        return db.habitDao().getHabitId(id)
    }*/
}
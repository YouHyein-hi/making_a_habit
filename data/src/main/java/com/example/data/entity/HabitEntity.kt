package com.example.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.HabitData

@Entity(tableName = "habit")
data class HabitEntity(
    @PrimaryKey (autoGenerate = true)
    val habitId: Int?,
    @ColumnInfo var habitName: String,
    @ColumnInfo var habitPeriod: String,
    @ColumnInfo var habitPeriodNum: Int?,
    @ColumnInfo var habitColor: String,
    @ColumnInfo var habitDateStart: String,
    @ColumnInfo var habitDateIng: String,
    @ColumnInfo var habitDateEnd: String,
    @ColumnInfo var habitRoundFull: Int,
    @ColumnInfo var habitLastRoundFull: Int,
    @ColumnInfo var habitComplete: Boolean,
    @ColumnInfo var habitComment: String

    )
fun HabitEntity.toDomainEntity() : HabitData = HabitData(
    id = habitId,
    name = habitName,
    period = habitPeriod,
    periodNum = habitPeriodNum,
    color = habitColor,
    dateStart = habitDateStart,
    dateIng = habitDateIng,
    dateEnd = habitDateEnd,
    roundFull = habitRoundFull,
    lastRoundFull = habitLastRoundFull,
    complete = habitComplete,
    comment = habitComment
)
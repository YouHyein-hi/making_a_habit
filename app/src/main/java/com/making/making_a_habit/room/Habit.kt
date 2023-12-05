package com.making.making_a_habit.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(
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
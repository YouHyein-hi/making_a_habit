package com.example.making_a_habit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey (autoGenerate = true)
    val habitId: Long?,

    @ColumnInfo
    var habitName: String,

    @ColumnInfo
    var habitPeriod: String,

    @ColumnInfo
    var habitPeriodNum: Int?,

    @ColumnInfo
    var habitColor: String,

    @ColumnInfo
    var habitDateStart: String,

    @ColumnInfo
    var habitDateEnd: String,

    @ColumnInfo
    var habitRoundFull: Int,

    @ColumnInfo
    var habitComplete: Boolean,

    @ColumnInfo
    var habitComment: String

    )
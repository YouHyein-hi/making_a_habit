package com.example.domain.model

data class HabitData (
    val id: Int?,
    val name: String,
    val period: String,
    val periodNum: Int?,
    val color: String,
    val dateStart: String,
    val dateIng: String,
    val dateEnd: String,
    val roundFull: Int,
    val lastRoundFull: Int,
    val complete: Boolean,
    val comment: String
)
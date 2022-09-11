package com.example.making_a_habit.model

data class DetailItem(
    val count: Int?,        // habitPeriodNum
    val color: String?,     // habitColor
    val period: String?,    // habitPeriod
    val dateIng: String?,   // habitDateIng
    val roundfull : Int?,   // habitRoundFull
    val lastround: Int?     // habitLastRoundFull
)
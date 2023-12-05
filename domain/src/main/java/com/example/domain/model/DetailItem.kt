package com.example.domain.model

data class DetailItem(
    val count: Int?,        // habitPeriodNum
    val color: String?,     // habitColor
    val period: String?,    // habitPeriod
    val dateIng: String?,   // habitDateIng
    val dateEnd: String,    // habitDateEnd
    val roundfull : Int?,   // habitRoundFull
    val lastround: Int?     // habitLastRoundFull
)
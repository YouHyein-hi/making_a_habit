package com.making.making_a_habit.viewmodel.fragmentViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.making.making_a_habit.HabitRepository

class HistoryDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HabitRepository(application)
}
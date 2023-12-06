package com.making.making_a_habit.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
// 액티비티 시작 전에 작동
class MyApplication: Application(
) {

}
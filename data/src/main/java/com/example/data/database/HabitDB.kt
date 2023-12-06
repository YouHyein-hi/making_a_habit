package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.HabitDAO
import com.example.data.entity.HabitEntity

@Database(entities = [HabitEntity::class], version = 4)
abstract class HabitDB : RoomDatabase() {
    abstract fun habitDao(): HabitDAO

    companion object {
        private var INSTANCE: HabitDB? = null

        fun getInstance(context: Context): HabitDB? {
            if (INSTANCE == null) {
                synchronized(HabitDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HabitDB::class.java, "habit_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}


package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.HabitDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext appContext: Context): HabitDB{
        return Room.databaseBuilder(appContext, HabitDB::class.java, "habitDB")
            .fallbackToDestructiveMigration()
            .build()
    }
}
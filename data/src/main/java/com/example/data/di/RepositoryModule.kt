package com.example.data.di

import com.example.data.room.database.HabitDB
import com.example.data.repolmpl.HabitRepositoryImpl
import com.example.domain.repository.HabitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepoRoom(db : HabitDB): HabitRepository {
        return HabitRepositoryImpl(db.habitDao())
    }
}
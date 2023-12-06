package com.example.data.di

import com.example.domain.repository.HabitRepository
import com.example.domain.usecase.DeleteIdUseCase
import com.example.domain.usecase.DeleteUseCase
import com.example.domain.usecase.GetAllUseCase
import com.example.domain.usecase.InsertUseCase
import com.example.domain.usecase.UpdateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllUseCase(repository: HabitRepository): GetAllUseCase{
        return GetAllUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertUseCase(repository: HabitRepository): InsertUseCase{
        return InsertUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteUseCase(repository: HabitRepository): DeleteUseCase{
        return DeleteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteIdUseCase(repository: HabitRepository): DeleteIdUseCase{
        return DeleteIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateUseCase(repository: HabitRepository): UpdateUseCase{
        return UpdateUseCase(repository)
    }

}
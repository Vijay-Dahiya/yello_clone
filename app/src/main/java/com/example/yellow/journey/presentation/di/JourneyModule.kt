package com.example.yellow.journey.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.yellow.journey.data.repo.BalanceRepositoryImpl
import com.example.yellow.journey.data.repo.TransactionRepositoryImpl
import com.example.yellow.journey.domain.repo.BalanceRepository
import com.example.yellow.journey.domain.repo.TransactionRepository

@Module
@InstallIn(SingletonComponent::class)
object JourneyModule {

    @Provides
    @Singleton
    fun provideBalanceRepository(): BalanceRepository {
        return BalanceRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(): TransactionRepository {
        return TransactionRepositoryImpl()
    }
}
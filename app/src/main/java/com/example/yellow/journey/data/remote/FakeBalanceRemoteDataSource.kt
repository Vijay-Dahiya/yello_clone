package com.example.yellow.journey.data.remote

import com.example.yellow.journey.domain.model.Balance
import kotlinx.coroutines.delay

object FakeBalanceRemoteDataSource {

    private val fakeBalance = Balance(amount = "19,981,150.00 ZAR")

    suspend fun fetchBalance(): Result<Balance> {
        delay(1500) // Simulate network delay
        // Always succeed, returning a fake balance
        return Result.success(fakeBalance)
    }
}

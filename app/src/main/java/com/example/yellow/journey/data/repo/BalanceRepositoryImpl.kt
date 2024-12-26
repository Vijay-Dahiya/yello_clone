package com.example.yellow.journey.data.repo

import com.example.yellow.journey.data.remote.FakeBalanceRemoteDataSource
import com.example.yellow.journey.domain.model.Balance
import com.example.yellow.journey.domain.repo.BalanceRepository

class BalanceRepositoryImpl : BalanceRepository {
    override suspend fun getBalance(): Result<Balance> {
        return FakeBalanceRemoteDataSource.fetchBalance()
    }
}

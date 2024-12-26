package com.example.yellow.journey.data.repo

import com.example.yellow.journey.data.remote.FakeTransactionRemoteDataSource
import com.example.yellow.journey.domain.model.TransactionResult
import com.example.yellow.journey.domain.repo.TransactionRepository


class TransactionRepositoryImpl : TransactionRepository {
    override suspend fun topUp(mobileNumber: String, amount: String): Result<TransactionResult> {
        return FakeTransactionRemoteDataSource.topUp(mobileNumber, amount)
    }
}
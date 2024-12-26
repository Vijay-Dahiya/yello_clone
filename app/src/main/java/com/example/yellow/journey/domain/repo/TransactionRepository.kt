package com.example.yellow.journey.domain.repo

import com.example.yellow.journey.domain.model.TransactionResult

interface TransactionRepository {
    suspend fun topUp(mobileNumber: String, amount: String): Result<TransactionResult>
}

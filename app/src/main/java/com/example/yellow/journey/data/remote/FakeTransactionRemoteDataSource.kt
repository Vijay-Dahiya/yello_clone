package com.example.yellow.journey.data.remote

import com.example.yellow.journey.domain.model.TransactionResult
import kotlinx.coroutines.delay

object FakeTransactionRemoteDataSource {

    suspend fun topUp(mobileNumber: String, amount: String): Result<TransactionResult> {
        delay(1500)

        return if (mobileNumber.isBlank() || amount.isBlank()) {
            Result.failure(Exception("Invalid input"))
        } else if (mobileNumber == "99999") {
            Result.failure(Exception("Server error, please try again."))
        } else {
            Result.success(TransactionResult("TopUp Successful!"))
        }
    }
}
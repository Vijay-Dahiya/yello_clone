package com.example.yellow.journey.domain.repo

import com.example.yellow.journey.domain.model.Balance

interface BalanceRepository {
    suspend fun getBalance(): Result<Balance>
}
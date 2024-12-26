package com.example.yellow.journey.presentation.ui.states

import com.example.yellow.journey.domain.model.Balance

data class BalanceUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val balance: Balance? = null
)
package com.example.yellow.journey.presentation.ui.states

data class TransactionUiState(
    val mobileNumber: String = "",
    val topUpAmount: String = "",
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
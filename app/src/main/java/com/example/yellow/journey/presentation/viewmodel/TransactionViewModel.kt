package com.example.yellow.journey.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.yellow.journey.domain.repo.TransactionRepository
import com.example.yellow.journey.presentation.ui.states.TransactionUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    var uiState by mutableStateOf(TransactionUiState())
        private set

    fun onMobileNumberChange(newValue: String) {
        uiState = uiState.copy(mobileNumber = newValue)
    }

    fun onSwipeComplete() {
        uiState = uiState.copy(isSuccess = true)
    }

    fun onTopUpAmountChange(newValue: String) {
        uiState = uiState.copy(topUpAmount = newValue)
    }

    fun topUp() {
        uiState = uiState.copy(isLoading = true, errorMessage = null, isSuccess = false)

        viewModelScope.launch {
            val result = transactionRepository.topUp(uiState.mobileNumber, uiState.topUpAmount)

            result.onSuccess {
                uiState = uiState.copy(isLoading = false, isSuccess = true)
            }.onFailure { ex ->
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = ex.message,
                    isSuccess = false
                )
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(errorMessage = null)
    }

    fun dismissSuccess() {
        uiState = uiState.copy(isSuccess = false)
    }
}

package com.example.yellow.journey.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.yellow.journey.domain.repo.BalanceRepository
import com.example.yellow.journey.presentation.ui.states.BalanceUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val balanceRepository: BalanceRepository
) : ViewModel() {

    var uiState by mutableStateOf(BalanceUiState())
        private set

    init {
        loadBalance()
    }

    fun loadBalance() {
        uiState = uiState.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = balanceRepository.getBalance()
            result.onSuccess { fetchedBalance ->
                uiState = uiState.copy(
                    isLoading = false,
                    balance = fetchedBalance,
                    errorMessage = null
                )
            }.onFailure { throwable ->
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = throwable.message,
                    balance = null
                )
            }
        }
    }
}

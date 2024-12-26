package com.example.yellow.auth.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yellow.auth.domain.repo.AuthRepository
import com.example.yellow.auth.presentation.ui.states.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onUsernameChange(newUsername: String) {
        uiState = uiState.copy(username = newUsername)
    }

    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    fun login() {
        // Reset error
        uiState = uiState.copy(errorMessage = null, isLoading = true)

        viewModelScope.launch {
            // (Optional) Artificial short delay before the actual "API" call
            delay(500)

            val result = authRepository.login(
                username = uiState.username,
                password = uiState.password
            )

            result.onSuccess { user ->
                // On success, set loginSuccess = true, or handle as needed
                uiState = uiState.copy(
                    isLoading = false,
                    loginSuccess = true,
                    errorMessage = null
                )
            }.onFailure { error ->
                // On failure, show the error
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = error.message,
                    loginSuccess = false
                )
            }
        }
    }

    // Example of an extension function usage
    // (Overly simple, but just to demonstrate)
    fun String.withAsterisks(): String {
        return "*** $this ***"
    }
}

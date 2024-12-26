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
        uiState = uiState.copy(errorMessage = null, isLoading = true)

        viewModelScope.launch {
            delay(500)

            val result = authRepository.login(
                username = uiState.username,
                password = uiState.password
            )

            result.onSuccess { user ->
                uiState = uiState.copy(
                    isLoading = false,
                    loginSuccess = true,
                    errorMessage = null
                )
            }.onFailure { error ->
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = error.message,
                    loginSuccess = false
                )
            }
        }
    }

    fun String.withAsterisks(): String {
        return "*** $this ***"
    }
}

package com.example.yellow.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yellow.auth.presentation.viewmodel.LoginViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val state = viewModel.uiState

    if (state.loginSuccess) {
        onLoginSuccess()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),){
        Text(text = "Login Screen", modifier = Modifier.align(Alignment.TopCenter).padding(top = 60.dp), fontSize = 22.sp)
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {

            OutlinedTextField(
                value = state.username,
                onValueChange = { viewModel.onUsernameChange(it) },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.login()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                Text(text = "Login")
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }

            state.errorMessage?.let { error ->
                Text(
                    text = error,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

}


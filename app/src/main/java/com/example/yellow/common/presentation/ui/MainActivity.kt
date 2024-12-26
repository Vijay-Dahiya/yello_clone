package com.example.yellow.common.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.yellow.auth.presentation.ui.screens.LoginScreen
import com.example.yellow.journey.presentation.ui.screens.MainScreen
import com.example.yellow.ui.theme.YellowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YellowTheme {
                YelloCloneApp()
            }
        }
    }
}

@Composable
fun YelloCloneApp() {
    // Track whether user is logged in or not.
    // Initially false, so user sees the login screen first.
    val isLoggedIn = remember { mutableStateOf(false) }

    MaterialTheme {
        Surface {
            if (isLoggedIn.value) {
                // After login, show MainScreen
                MainScreen()
            } else {
                // Show a dummy login screen
                LoginScreen(
                    onLoginSuccess = {
                        isLoggedIn.value = true
                    }
                )
            }
        }
    }
}
package com.example.yellow.journey.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yellow.journey.presentation.ui.components.SwipeToTopUpButton
import com.example.yellow.journey.presentation.ui.components.TransactionSuccessBottomSheet
import com.example.yellow.journey.presentation.viewmodel.TransactionViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    // If there's an error message, show a Toast
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    // A coroutine scope for showing/hiding the sheet
    val scope = rememberCoroutineScope()

    // Create a bottom sheet state that starts hidden
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // or false if you want half expansion
    )

    // If isSuccess changes, show/hide bottom sheet
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            scope.launch { sheetState.show() }
        } else {
            scope.launch { sheetState.hide() }
        }
    }

    // The bottom sheet
    if (state.isSuccess){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { viewModel.dismissSuccess() }
        ) {
            // Because isSuccess triggers the sheet, we display the user’s typed text:
            TransactionSuccessBottomSheet(
                mobileNumber = state.mobileNumber,
                amount = state.topUpAmount,
                onPrintReceipt = {
                    // handle your print logic, or just ignore
                },
                onDismiss = {
                    // user taps "dismiss"
                    viewModel.dismissSuccess()
                }
            )
        }
    }

    // The main screen content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Customer Mobile Number")
        OutlinedTextField(
            value = state.mobileNumber,
            onValueChange = { viewModel.onMobileNumberChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Text("Topup Amount")
        OutlinedTextField(
            value = state.topUpAmount,
            onValueChange = { viewModel.onTopUpAmountChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // The real swipe button
        SwipeToTopUpButton(
            isLoading = state.isLoading,
            onSwipeComplete = {
                // Instead of a network call, we set success immediately:
                viewModel.onSwipeComplete()
                // Or call viewModel.topUp() if you want a dummy call
            }
        )
    }
}

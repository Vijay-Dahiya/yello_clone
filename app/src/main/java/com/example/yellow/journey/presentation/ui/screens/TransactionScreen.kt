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

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            scope.launch { sheetState.show() }
        } else {
            scope.launch { sheetState.hide() }
        }
    }

    if (state.isSuccess){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { viewModel.dismissSuccess() }
        ) {
            TransactionSuccessBottomSheet(
                mobileNumber = state.mobileNumber,
                amount = state.topUpAmount,
                onPrintReceipt = {
                },
                onDismiss = {
                    viewModel.dismissSuccess()
                }
            )
        }
    }

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

        SwipeToTopUpButton(
            isLoading = state.isLoading,
            onSwipeComplete = {
                viewModel.onSwipeComplete()
            }
        )
    }
}


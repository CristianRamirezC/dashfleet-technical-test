package com.example.dashfleet_technical_test.ui.view.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel

@Composable
fun LoginScreen(userLoginViewModel: UserLoginViewModel) {

    val userPhoneNumber: String by userLoginViewModel
        .userPhoneNumber
        .observeAsState(initial = "")

    val userPassword: String by userLoginViewModel
        .userPassword
        .observeAsState(initial = "")

    val isLoginButtonEnabled: Boolean by userLoginViewModel
        .isLoginButtonEnabled
        .observeAsState(initial = false)

    val isErrorLogging: Boolean by userLoginViewModel
        .isErrorLogging
        .observeAsState(initial = false)

    val isLoading: Boolean by userLoginViewModel
        .isLoading
        .observeAsState(initial = false)

    Scaffold(
        topBar = {
            LoginTopBar()
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            PhoneNumberField(userPhoneNumber = userPhoneNumber) { phoneNumber ->
                userLoginViewModel.onLoginChanged(
                    userPhoneNumber = phoneNumber,
                    userPassword = userPassword
                )
            }
            PasswordField(userPassword = userPassword) { password ->
                userLoginViewModel.onLoginChanged(
                    userPhoneNumber = userPhoneNumber,
                    userPassword = password
                )
            }
            RememberUserToggle(isErrorLogging)
            LoginButton(isLoginButtonEnabled, userLoginViewModel, isLoading)
        }
    }
}



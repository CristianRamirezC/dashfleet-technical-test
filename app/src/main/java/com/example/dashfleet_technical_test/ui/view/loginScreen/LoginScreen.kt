package com.example.dashfleet_technical_test.ui.view.loginScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    ) {

        Column() {
            PhoneNumberField(userPhoneNumber = userPhoneNumber) {
                userLoginViewModel.onLoginChanged(userPhoneNumber = it, userPassword = userPassword)
            }
            PasswordField(userPassord = userPassword) {
                userLoginViewModel.onLoginChanged(
                    userPhoneNumber = userPhoneNumber,
                    userPassword = it
                )
            }
            RememberUserToggle(isErrorLogging)
            LoginButton(isLoginButtonEnabled, userLoginViewModel, isLoading)
        }
    }
}



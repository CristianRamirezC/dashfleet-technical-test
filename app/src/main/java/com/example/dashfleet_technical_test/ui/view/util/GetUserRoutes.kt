package com.example.dashfleet_technical_test.ui.view.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel

@Composable
fun GetUserRoutes(
    userLoginViewModel: UserLoginViewModel,
    userRoutesViewModel: UserRoutesViewModel
) {
    val userPhoneNumber by userLoginViewModel
        .userLoginPhoneNumber
        .observeAsState()

    userRoutesViewModel.getUserRoutes(userPhoneNumber!!)

}
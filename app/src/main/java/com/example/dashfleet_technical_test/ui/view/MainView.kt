package com.example.dashfleet_technical_test.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.dashfleet_technical_test.ui.theme.DashfleetTechnicalTestTheme
import com.example.dashfleet_technical_test.ui.view.navigation.MainNavigationScreenView
import com.example.dashfleet_technical_test.ui.view.loginScreen.LoginScreen
import com.example.dashfleet_technical_test.ui.view.util.GetUserRoutes
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel

@Composable
fun MainView(
    userLoginViewModel: UserLoginViewModel,
    userRoutesViewModel: UserRoutesViewModel
) {
    DashfleetTechnicalTestTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val isUserLogged by userLoginViewModel.userAbleToLogin.observeAsState(initial = false)
            if (isUserLogged) {
                GetUserRoutes(userLoginViewModel, userRoutesViewModel)
                MainNavigationScreenView(userLoginViewModel, userRoutesViewModel)
            } else {
                LoginScreen(userLoginViewModel = userLoginViewModel)
            }
        }
    }
}



package com.example.dashfleet_technical_test.ui.view.bottomNavigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import com.example.dashfleet_technical_test.ui.view.util.LoadingView
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainNavigationScreenView(userLoginViewModel: UserLoginViewModel) {
    val isLoading by userLoginViewModel.isLoading.observeAsState(initial = false)

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MainBottomNavigation(navController = navController)
        }
    ) {
        if (isLoading) {
            LoadingView()
        } else {
            NavigationGraph(navController = navController)
        }
    }
}
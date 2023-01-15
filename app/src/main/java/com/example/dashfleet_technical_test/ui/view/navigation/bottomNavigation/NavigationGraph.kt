package com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashfleet_technical_test.ui.view.homeScreen.HomeScreen
import com.example.dashfleet_technical_test.ui.view.routesScreen.RoutesScreen
import com.example.dashfleet_technical_test.ui.view.userScreen.UserScreen
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    userRoutesViewModel: UserRoutesViewModel,
    userLoginViewModel: UserLoginViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.screenRoute
    ) {
        composable(route = NavItem.Home.screenRoute) {
            HomeScreen()
        }

        composable(route = NavItem.Routes.screenRoute) {
            RoutesScreen(userRoutesViewModel, paddingValues)
        }

        composable(route = NavItem.User.screenRoute) {
            UserScreen(userLoginViewModel)
        }
    }
}
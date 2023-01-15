package com.example.dashfleet_technical_test.ui.view.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashfleet_technical_test.ui.view.homeScreen.HomeScreen
import com.example.dashfleet_technical_test.ui.view.routesScreen.RoutesScreen
import com.example.dashfleet_technical_test.ui.view.userScreen.UserScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screenRoute
    ) {
        composable(route = BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }

        composable(route = BottomNavItem.Routes.screenRoute) {
            RoutesScreen()
        }

        composable(route = BottomNavItem.User.screenRoute) {
            UserScreen()
        }
    }
}
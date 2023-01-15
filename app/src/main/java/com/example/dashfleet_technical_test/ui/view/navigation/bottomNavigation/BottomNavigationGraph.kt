package com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashfleet_technical_test.ui.view.homeScreen.HomeScreen
import com.example.dashfleet_technical_test.ui.view.routesScreen.RoutesScreen
import com.example.dashfleet_technical_test.ui.view.userScreen.UserScreen

@Composable
fun BottomNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.screenRoute
    ) {
        composable(route = NavItem.Home.screenRoute) {
            HomeScreen()
        }

        composable(route = NavItem.Routes.screenRoute) {
            RoutesScreen()
        }

        composable(route = NavItem.User.screenRoute) {
            UserScreen()
        }
    }
}
package com.example.dashfleet_technical_test.ui.view.navigation.lateralNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashfleet_technical_test.ui.view.homeScreen.HomeScreen
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.NavItem
import com.example.dashfleet_technical_test.ui.view.routesScreen.RoutesScreen
import com.example.dashfleet_technical_test.ui.view.userScreen.UserScreen

@Composable
fun LateralNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.screenRoute
    ) {
        composable(NavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(NavItem.Routes.screenRoute) {
            RoutesScreen()
        }
        composable(NavItem.User.screenRoute) {
            UserScreen()
        }
    }
}
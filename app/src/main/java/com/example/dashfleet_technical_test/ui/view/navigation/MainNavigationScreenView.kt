package com.example.dashfleet_technical_test.ui.view.bottomNavigation

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.MainBottomNavigation
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.NavItem
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.BottomNavigationGraph
import com.example.dashfleet_technical_test.ui.view.navigation.lateralNavigation.Drawer
import com.example.dashfleet_technical_test.ui.view.navigation.lateralNavigation.LateralNavigationGraph
import com.example.dashfleet_technical_test.ui.view.topBar.MainTopBar
import com.example.dashfleet_technical_test.ui.view.util.LoadingView
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainNavigationScreenView(userLoginViewModel: UserLoginViewModel) {
    val isLoading by userLoginViewModel.isLoading.observeAsState(initial = false)

    val navController: NavHostController = rememberNavController()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val routeDestinations: List<NavItem> = listOf(
        NavItem.Home,
        NavItem.Routes,
        NavItem.User
    )
    Scaffold(
        bottomBar = {
            MainBottomNavigation(navController = navController, bottomNavItems = routeDestinations)
        },
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(scope, scaffoldState)
        },
        drawerContent = {
            Drawer(
                lateralNavItem = routeDestinations,
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }
    ) {
        if (isLoading) {
            LoadingView()
        } else {
            LateralNavigationGraph(navController = navController)
            BottomNavigationGraph(navController = navController)
        }
    }
}



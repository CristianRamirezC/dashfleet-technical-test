package com.example.dashfleet_technical_test.ui.view.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.BottomNavigationDrawer
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.NavItem
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.NavigationGraph
import com.example.dashfleet_technical_test.ui.view.navigation.SideNavigation.Drawer
import com.example.dashfleet_technical_test.ui.view.topBar.MainTopBar
import com.example.dashfleet_technical_test.ui.view.util.LoadingView
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainNavigationScreenView(
    userLoginViewModel: UserLoginViewModel,
    userRoutesViewModel: UserRoutesViewModel
) {
    val isLoadingLogin by userLoginViewModel.isLoading.observeAsState(initial = false)
    val isLoadingGetRoutes by userRoutesViewModel.isLoading.observeAsState(initial = false)

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
            BottomNavigationDrawer(navController = navController, bottomNavItems = routeDestinations)
        },
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(scope, scaffoldState, userLoginViewModel)
        },
        drawerContent = {
            Drawer(
                sideNavItem = routeDestinations,
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController,
                userLoginViewModel = userLoginViewModel,
            )
        }
    ) {
        if (isLoadingGetRoutes || isLoadingLogin) {
            LoadingView()
        } else {
            NavigationGraph(
                navController = navController,
                userRoutesViewModel = userRoutesViewModel,
                userLoginViewModel = userLoginViewModel,
                paddingValues = it
            )
        }
    }
}



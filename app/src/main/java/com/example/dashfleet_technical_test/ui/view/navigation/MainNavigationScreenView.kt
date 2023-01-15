package com.example.dashfleet_technical_test.ui.view.bottomNavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.dashfleet_technical_test.R
import com.example.dashfleet_technical_test.ui.view.lateralNavigation.Drawer
import com.example.dashfleet_technical_test.ui.view.topBar.MainTopBar
import com.example.dashfleet_technical_test.ui.view.util.LoadingView
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainNavigationScreenView(userLoginViewModel: UserLoginViewModel) {
    val isLoading by userLoginViewModel.isLoading.observeAsState(initial = false)

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
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
        drawerContent = { Drawer(lateralNavItem = routeDestinations) }
    ) {
        if (isLoading) {
            LoadingView()
        } else {
            NavigationGraph(navController = navController)
        }
    }
}



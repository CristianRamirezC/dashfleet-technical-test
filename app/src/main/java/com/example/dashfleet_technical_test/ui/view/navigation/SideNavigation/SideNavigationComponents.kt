package com.example.dashfleet_technical_test.ui.view.navigation.SideNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dashfleet_technical_test.R
import com.example.dashfleet_technical_test.core.constants.AppStringConstants
import com.example.dashfleet_technical_test.ui.view.navigation.bottomNavigation.NavItem
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    sideNavItem: List<NavItem>,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    userLoginViewModel: UserLoginViewModel
) {
    DrawerHeader(userLoginViewModel)
    Column() {
        sideNavItem.forEach { navItem ->
            ItemDrawer(navItem = navItem) {
                navController.navigate(navItem.screenRoute) {
                    //avoid creating new instances if is already on top on stack
                    navController.graph.startDestinationRoute?.let { screenRoute ->
                        popUpTo(screenRoute) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}

@Composable
fun DrawerHeader(
    userLoginViewModel: UserLoginViewModel
) {

    val userName by userLoginViewModel.userName.observeAsState(initial = "")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp),
    ) {
        Card(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.CenterHorizontally),
            shape = CircleShape,
            elevation = 2.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = AppStringConstants.PROFILE_PICTURE
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = userName, fontSize = 20.sp
        )
    }
}

@Composable
fun ItemDrawer(
    modifier: Modifier = Modifier,
    navItem: NavItem,
    onItemSelected: (NavItem) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10))
            .padding(8.dp)
            .clickable { onItemSelected(navItem) }
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(navItem.icon),
            contentDescription = navItem.title
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = navItem.title,
            fontSize = 20.sp
        )
    }
}
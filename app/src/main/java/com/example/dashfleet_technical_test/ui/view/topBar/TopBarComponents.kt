package com.example.dashfleet_technical_test.ui.view.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.R
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    userLoginViewModel: UserLoginViewModel,

    ) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            TopBarProfilePicture()
            TopBarUserInfo(userLoginViewModel)
            TopBarLateralMenuButton(scope, scaffoldState)
        }
    }
}

@Composable
fun TopBarLateralMenuButton(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    Box(modifier = Modifier) {
        IconButton(
            modifier = Modifier.size(40.dp),
            onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu button"
            )
        }
    }
}

@Composable
fun TopBarUserInfo(
    userLoginViewModel: UserLoginViewModel,
) {

    val userName by userLoginViewModel.userName.observeAsState()
    val userPhoneNumber by userLoginViewModel.userPhoneNumber.observeAsState()
    Column(
        modifier = Modifier
    ) {
        Text(
            text = userName!!,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier,
            text = userPhoneNumber!!
        )
    }
}

@Composable
fun TopBarProfilePicture() {
    Card(
        modifier = Modifier.size(90.dp),
        shape = CircleShape,
        elevation = 2.dp
    ) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "profile picture"
        )
    }
}
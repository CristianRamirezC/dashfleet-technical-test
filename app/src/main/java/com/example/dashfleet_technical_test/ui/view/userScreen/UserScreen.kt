package com.example.dashfleet_technical_test.ui.view.userScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.core.constants.AppStringConstants
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel

@Composable
fun UserScreen(
    userLoginViewModel: UserLoginViewModel,
) {
    val userName by userLoginViewModel.userName.observeAsState()
    val userPhoneNumber by userLoginViewModel.userPhoneNumber.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = userName!!,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = userPhoneNumber!!,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(30.dp))
        LogoutButton(userLoginViewModel)
    }
}

@Composable
fun LogoutButton(
    userLoginViewModel: UserLoginViewModel,
) {
    Button(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        onClick = {
            userLoginViewModel.logoutUser()
        },
    ) {
        Text(
            modifier = Modifier,
            text = AppStringConstants.LOGOUT,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

package com.example.dashfleet_technical_test.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import com.example.dashfleet_technical_test.ui.theme.DashfleetTechnicalTestTheme
import com.example.dashfleet_technical_test.ui.view.bottomNavigation.MainNavigationScreenView
import com.example.dashfleet_technical_test.ui.view.loginScreen.LoginScreen
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userLoginViewModel: UserLoginViewModel by viewModels()
    private val userRoutesViewModel: UserRoutesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView(userLoginViewModel, userRoutesViewModel)
        }
    }
}


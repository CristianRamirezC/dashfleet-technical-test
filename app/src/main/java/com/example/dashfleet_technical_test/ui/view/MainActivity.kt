package com.example.dashfleet_technical_test.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import com.example.dashfleet_technical_test.ui.theme.DashfleetTechnicalTestTheme
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
        val phoneNumber: String = "3136756532"
        userLoginViewModel.loginUser(phoneNumber, "dashfleet2")
        userRoutesViewModel.getUserRoutes(phoneNumber)

        setContent {
            DashfleetTechnicalTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(userLoginViewModel)
//                    Greeting(userViewModel, userRoutesViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(userViewModel: UserLoginViewModel, userRoutesViewModel: UserRoutesViewModel) {

    val name: String by userViewModel.userName.observeAsState(initial = "")
    val userRoutes: UserRoutes by userRoutesViewModel.userRoutes.observeAsState(
        initial = UserRoutes(
            null,
            null
        )
    )
    Column() {
        Text(text = "Hello $name!")
        userRoutes.routes?.forEach { route ->
            Text(text = route.routeName)
        }
    }


}
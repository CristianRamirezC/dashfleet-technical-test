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
import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.example.dashfleet_technical_test.ui.theme.DashfleettechnicaltestTheme
import com.example.dashfleet_technical_test.ui.viewModel.user.UserViewModel
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private val userRoutesViewModel: UserRoutesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val phoneNumber: String = "3136756531"
        userViewModel.loginUser(phoneNumber, "dashfleet")
        userRoutesViewModel.getUserRoutes(phoneNumber)

        setContent {
            DashfleettechnicaltestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(userViewModel, userRoutesViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(userViewModel: UserViewModel, userRoutesViewModel: UserRoutesViewModel) {

    val name: String by userViewModel.userName.observeAsState(initial = "")
    val userRoutes: UserRoutesModel by userRoutesViewModel.userRoutes.observeAsState(
        initial = UserRoutesModel(
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
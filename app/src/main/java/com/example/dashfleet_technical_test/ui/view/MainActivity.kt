package com.example.dashfleet_technical_test.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
        //validations with build types/flavors

//        if(BuildConfig.BUILD_TYPE == "release") {
//            //Do something
//        }
//
//        val endpointURL = BuildConfig.URL
//        if (BuildConfig.FLAVOR == "premium") {
//            //Do something for premium users
//
//        }
    }
}


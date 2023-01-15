package com.example.dashfleet_technical_test.ui.view.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.R
import com.example.dashfleet_technical_test.ui.viewModel.user.UserLoginViewModel
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "This is the Home Screen",
            fontSize = 20.sp
        )
    }
}

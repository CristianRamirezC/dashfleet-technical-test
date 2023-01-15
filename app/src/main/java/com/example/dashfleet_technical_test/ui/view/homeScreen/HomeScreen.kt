package com.example.dashfleet_technical_test.ui.view.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { MainTopBar() },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}


@Composable
fun MainTopBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
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
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)) {
                Text(
                    text = "Nombre",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
                Text(text = "3136756531")
            }
            Text(text = "Lateral")
        }
    }
}
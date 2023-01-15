package com.example.dashfleet_technical_test.ui.view.routesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.domain.model.userRoutes.Route
import com.example.dashfleet_technical_test.ui.viewModel.userRoutes.UserRoutesViewModel
import com.example.dashfleet_technical_test.R


@Composable
fun RoutesScreen(
    userRoutesViewModel: UserRoutesViewModel,
    paddingValues: PaddingValues
) {
    val userRoutes by userRoutesViewModel.userRoutes.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding
                (
                top = 30.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp,
            text = "Your routes"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            LazyColumn {
                items(
                    items = userRoutes?.routes!!,
                    itemContent = { item ->
                        RenderRouteItem(route = item)
                    }
                )
            }
        }
    }
}


@Composable
fun RenderRouteItem(
    route: Route
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(12.dp),
        elevation = 4.dp
    ) {
        Row() {
            Image(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.route),
                contentDescription = "route"
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = route.routeName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Distance: " + route.routeDistance.toString() + "Km"
                )
            }
        }
    }
}
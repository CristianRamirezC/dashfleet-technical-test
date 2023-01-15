package com.example.dashfleet_technical_test.ui.view.lateralNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashfleet_technical_test.R
import com.example.dashfleet_technical_test.ui.view.bottomNavigation.NavItem

@Composable
fun Drawer(lateralNavItem: List<NavItem>) {
    DrawerHeader()
    Column() {
        lateralNavItem.forEach { navItem ->
            ItemDrawer(navItem = navItem)
        }
    }
}

@Composable
fun DrawerHeader() {
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
                contentDescription = "Profile picture"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Name", fontSize = 20.sp
        )
    }
}

@Composable
fun ItemDrawer(modifier: Modifier = Modifier, navItem: NavItem) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10))
            .padding(8.dp)
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
package com.example.dashfleet_technical_test.ui.view.bottomNavigation

import com.example.dashfleet_technical_test.R

sealed class NavItem(
    var title: String,
    var icon: Int,
    var screenRoute: String
) {
    object Home : NavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        screenRoute = "home"
    )

    object Routes : NavItem(
        title = "Routes",
        icon = R.drawable.ic_routes,
        screenRoute = "routes"
    )

    object User : NavItem(
        title = "User",
        icon = R.drawable.ic_user,
        screenRoute = "user"
    )
}
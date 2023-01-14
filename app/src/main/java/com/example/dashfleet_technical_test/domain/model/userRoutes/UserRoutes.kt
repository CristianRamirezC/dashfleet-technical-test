package com.example.dashfleet_technical_test.domain.model.userRoutes

import com.example.dashfleet_technical_test.data.model.userRoutes.RouteModel
import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel

data class UserRoutes(
    val routes: List<Route>? = emptyList(),
    val userId: Long? = 0L
)

//Mapper from UserRoutesModel (Data layer Model) to UserRoutes(Domain model)
fun UserRoutesModel.toDomain() = UserRoutes(
    routes?.map { it.toDomain() },
    userId
)
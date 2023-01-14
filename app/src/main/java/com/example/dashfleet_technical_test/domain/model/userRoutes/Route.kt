package com.example.dashfleet_technical_test.domain.model.userRoutes

import com.example.dashfleet_technical_test.core.constants.FirestoreConstants
import com.example.dashfleet_technical_test.data.model.userRoutes.RouteModel
import com.google.gson.annotations.SerializedName

data class Route(
    val routeName: String = "",
    val routeDistance: Int = 0
)

//Mapper from RouteModel (Data layer Model) to Route(Domain model)
fun RouteModel.toDomain() = Route(routeName, routeDistance)
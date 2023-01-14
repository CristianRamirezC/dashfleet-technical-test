package com.example.dashfleet_technical_test.data.model.userRoutes

import com.example.dashfleet_technical_test.core.constants.FirestoreConstants
import com.google.gson.annotations.SerializedName

data class RouteModel(
    @SerializedName(FirestoreConstants.ROUTE_NAME) val routeName: String = "",
    @SerializedName(FirestoreConstants.ROUTE_DISTANCE) val routeDistance: Int = 0
)
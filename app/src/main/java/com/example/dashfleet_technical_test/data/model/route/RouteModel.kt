package com.example.dashfleet_technical_test.data.model.route

import com.google.gson.annotations.SerializedName

data class RouteModel(
    @SerializedName("routeName") val routeName: String,
    @SerializedName("routeDistance") val routeDistance: Int
)
package com.example.dashfleet_technical_test.data.model.user

import com.example.dashfleet_technical_test.data.model.route.RouteModel
import com.google.gson.annotations.SerializedName

data class UserRoutesModel(
    @SerializedName("routes") val userRoutes: List<RouteModel>,
    @SerializedName("user-id") val userId: Int
)
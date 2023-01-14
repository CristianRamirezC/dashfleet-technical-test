package com.example.dashfleet_technical_test.data.model.userRoutes

import com.example.dashfleet_technical_test.data.model.userRoutes.RouteModel
import com.google.gson.annotations.SerializedName

data class UserRoutesModel(
    @SerializedName("routes") val routes: List<RouteModel>? = emptyList(),
    @SerializedName("userId") val userId: Long? = 0L
)
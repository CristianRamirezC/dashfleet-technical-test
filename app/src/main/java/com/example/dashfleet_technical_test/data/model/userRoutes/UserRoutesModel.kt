package com.example.dashfleet_technical_test.data.model.userRoutes

import com.example.dashfleet_technical_test.core.constants.FirestoreConstants
import com.google.gson.annotations.SerializedName

data class UserRoutesModel(
    @SerializedName(FirestoreConstants.ROUTES) val routes: List<RouteModel>? = emptyList(),
    @SerializedName(FirestoreConstants.USER_ID) val userId: Long? = 0L
)
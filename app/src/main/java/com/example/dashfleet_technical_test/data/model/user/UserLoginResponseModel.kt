package com.example.dashfleet_technical_test.data.model.user

import com.google.gson.annotations.SerializedName

data class UserLoginResponseModel(
    @SerializedName("id") val userId: Int?,
    @SerializedName("name") val userName: String?,
    @SerializedName("phoneNumber") val userPhoneNumber: Int?,
    @SerializedName("ableToLogin") val ableToLogin: Boolean
)

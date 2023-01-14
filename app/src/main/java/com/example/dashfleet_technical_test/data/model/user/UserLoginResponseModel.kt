package com.example.dashfleet_technical_test.data.model.user

import com.google.gson.annotations.SerializedName

data class UserLoginResponseModel(
    @SerializedName("id") val userId: Long?,
    @SerializedName("name") val userName: String?,
    @SerializedName("phoneNumber") val userPhoneNumber: String?,
    @SerializedName("ableToLogin") val ableToLogin: Boolean
)

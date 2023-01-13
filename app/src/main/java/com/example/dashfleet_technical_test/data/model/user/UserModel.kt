package com.example.dashfleet_technical_test.data.model.user

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val userId: Int,
    @SerializedName("name") val userName: String,
    @SerializedName("password") val userPassword: String
)

package com.example.dashfleet_technical_test.data.model.user

import com.example.dashfleet_technical_test.core.constants.FirestoreConstants
import com.google.gson.annotations.SerializedName

data class UserLoginResponseModel(
    @SerializedName(FirestoreConstants.ID) val userId: Long?,
    @SerializedName(FirestoreConstants.NAME) val userName: String?,
    @SerializedName(FirestoreConstants.PHONE_NUMBER) val userPhoneNumber: String?,
    @SerializedName(FirestoreConstants.ABLE_TO_LOGIN) val ableToLogin: Boolean,
)

package com.example.dashfleet_technical_test.domain.model.user

import com.example.dashfleet_technical_test.data.model.user.UserLoginResponseModel

data class UserLoginResponse(
    val userId: Long?,
    val userName: String?,
    val userPhoneNumber: String?,
    val ableToLogin: Boolean
)

//Mapper from UserLoginResponseModel (Data layer Model) to UserLoginResponse(Domain model)
fun UserLoginResponseModel.toDomain() =
    UserLoginResponse(userId, userName, userPhoneNumber, ableToLogin)

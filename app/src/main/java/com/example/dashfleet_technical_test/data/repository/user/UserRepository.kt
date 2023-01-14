package com.example.dashfleet_technical_test.data.repository.user

import com.example.dashfleet_technical_test.data.model.user.UserLoginResponseModel
import com.example.dashfleet_technical_test.data.network.user.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserService
) {
    suspend fun loginUser(userPhoneNumber: Int, userPassword: String): UserLoginResponseModel {
        return api.loginUser(userPhoneNumber, userPassword)
    }
}
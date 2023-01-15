package com.example.dashfleet_technical_test.data.network.user

import com.example.dashfleet_technical_test.data.model.user.UserLoginResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(
    private val api: UserApiClient
) {
    suspend fun loginUser(
        userPhoneNumber: String,
        userPassword: String
    ): UserLoginResponseModel {
        return withContext(Dispatchers.IO) {
            val response = api.getUser(userPhoneNumber)
            if (response.userPassword == userPassword) {
                UserLoginResponseModel(
                    response.userId,
                    response.userName,
                    response.userPhoneNumber,
                    true
                )
            } else {
                UserLoginResponseModel(
                    null,
                    null,
                    null,
                    false,
                )
            }
        }
    }
}

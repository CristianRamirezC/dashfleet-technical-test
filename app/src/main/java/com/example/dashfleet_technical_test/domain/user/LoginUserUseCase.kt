package com.example.dashfleet_technical_test.domain.user

import com.example.dashfleet_technical_test.data.model.user.UserLoginResponseModel
import com.example.dashfleet_technical_test.data.repository.user.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userPhoneNumber: String, userPassword: String): UserLoginResponseModel {
        return repository.loginUser(
            userPhoneNumber = userPhoneNumber,
            userPassword = userPassword
        )
    }
}
package com.example.dashfleet_technical_test.domain.user

import com.example.dashfleet_technical_test.data.repository.user.UserRepository
import com.example.dashfleet_technical_test.domain.model.user.UserLoginResponse
import javax.inject.Inject

class LoginUserFirestoreUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userPhoneNumber: String, userPassword: String): UserLoginResponse {
        return repository.loginUserFirestore(
            userPhoneNumber = userPhoneNumber,
            userPassword = userPassword
        )
    }
}
package com.example.dashfleet_technical_test.domain.userRoutes.userRoutes

import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.example.dashfleet_technical_test.data.repository.userRoutes.UserRoutesRepository
import javax.inject.Inject

class GetUserRoutesFirestoreUseCase @Inject constructor(
    private val repository: UserRoutesRepository
) {
    suspend operator fun invoke(userPhoneNumber: String): UserRoutesModel {
        return repository.getUserRouteFirestore(
            userPhoneNumber = userPhoneNumber
        )
    }
}
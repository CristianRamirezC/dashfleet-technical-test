package com.example.dashfleet_technical_test.domain.userRoutes

import com.example.dashfleet_technical_test.data.repository.userRoutes.UserRoutesRepository
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import javax.inject.Inject

class GetUserRoutesFirestoreUseCase @Inject constructor(
    private val repository: UserRoutesRepository
) {
    suspend operator fun invoke(userPhoneNumber: String): UserRoutes {
        return repository.getUserRouteFirestore(
            userPhoneNumber = userPhoneNumber
        )
    }
}
package com.example.dashfleet_technical_test.data.repository.userRoutes

import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.example.dashfleet_technical_test.data.network.userRoutes.UserRoutesService
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import com.example.dashfleet_technical_test.domain.model.userRoutes.toDomain
import javax.inject.Inject

class UserRoutesRepository @Inject constructor(
    private val api: UserRoutesService
) {
    suspend fun getUserRouteFirestore(userPhoneNumber: String): UserRoutesModel {
        return api.getUserRoutes(userPhoneNumber)
    }
}
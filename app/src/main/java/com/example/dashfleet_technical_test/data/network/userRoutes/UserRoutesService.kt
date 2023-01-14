package com.example.dashfleet_technical_test.data.network.userRoutes

import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRoutesService @Inject constructor(
    private val api: UserRoutesApiClient
) {
    suspend fun getUserRoutes(userPhoneNumber: String): UserRoutesModel {
        return withContext(Dispatchers.IO) {
            val response = api.getUserRoutes(userPhoneNumber)
            response
        }
    }
}
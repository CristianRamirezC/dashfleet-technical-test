package com.example.dashfleet_technical_test.data.network.userRoutes

import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRoutesService @Inject constructor(
    private val api: UserRoutesApiClient
) {
    fun getUserRoutes(userPhoneNumber: String): Observable<UserRoutes> {
        return withContext(Dispatchers.IO) {
            val response = api.getUserRoutes(userPhoneNumber)
            response
        }
    }
}
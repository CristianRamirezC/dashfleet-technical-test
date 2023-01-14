package com.example.dashfleet_technical_test.data.network.userRoutes

import com.example.dashfleet_technical_test.data.model.userRoutes.RouteModel
import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRoutesApiClient @Inject constructor(
    private val db: FirebaseFirestore
) {
    suspend fun getUserRoutes(userPhoneNumber: String): UserRoutesModel {
        val userRoutes = db.
        collection("userRoutes").
        document(userPhoneNumber).
        get().
        await().
        toObject(UserRoutesModel::class.java)

        return UserRoutesModel(
            routes = userRoutes?.routes,
            userId = userRoutes?.userId
        )
    }
}
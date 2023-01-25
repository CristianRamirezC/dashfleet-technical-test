package com.example.dashfleet_technical_test.data.network.userRoutes

import com.example.dashfleet_technical_test.core.constants.FirestoreConstants
import com.example.dashfleet_technical_test.data.model.userRoutes.RouteModel
import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRoutesApiClient @Inject constructor(
    private val db: FirebaseFirestore
) {
    suspend fun getUserRoutes(userPhoneNumber: String): UserRoutesModel {
        return try {
            val userRoutes =
                db
                    .collection(FirestoreConstants.USER_ROUTES)
                    .document(userPhoneNumber)
                    .get()
                    .await()
                    .toObject(UserRoutesModel::class.java)

            UserRoutesModel(
                routes = userRoutes?.routes,
                userId = userRoutes?.userId
            )
        } catch (e: Exception) {
            UserRoutesModel()
        }
    }
}
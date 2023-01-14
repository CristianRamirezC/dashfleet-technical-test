package com.example.dashfleet_technical_test.data.network.user

import com.example.dashfleet_technical_test.data.model.user.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserApiClient @Inject constructor(
    private val db: FirebaseFirestore
) {
    suspend fun getUser(userPhoneNumber: String): UserModel {
        val user = db.
        collection("users").
        document(userPhoneNumber).
        get().
        await()
        return UserModel(
            userId = user.get("id") as Long,
            userName = user.get("name") as String,
            userPassword = user.get("password") as String,
            userPhoneNumber = user.get("phoneNumber") as String
        )
    }
}
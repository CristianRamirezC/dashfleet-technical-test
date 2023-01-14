package com.example.dashfleet_technical_test.data.network.user

import com.example.dashfleet_technical_test.core.constants.FirestoreConstants
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
        collection(FirestoreConstants.USERS).
        document(userPhoneNumber).
        get().
        await()
        return UserModel(
            userId = user.get(FirestoreConstants.ID) as Long,
            userName = user.get(FirestoreConstants.NAME) as String,
            userPassword = user.get(FirestoreConstants.PASSWORD) as String,
            userPhoneNumber = user.get(FirestoreConstants.PHONE_NUMBER) as String
        )
    }
}
package com.example.dashfleet_technical_test.data.network.user

import com.example.dashfleet_technical_test.data.model.user.UserLoginResponseModel
import com.example.dashfleet_technical_test.data.model.user.UserModel
import com.google.firebase.firestore.FirebaseFirestore

class UserApiClient {
    suspend fun getUser(userPhoneNumber: Int): UserModel {
        val db = FirebaseFirestore.getInstance()
        lateinit var user: UserModel
        db.collection("users").document(userPhoneNumber.toString()).get().addOnSuccessListener {
            user = UserModel(
                userId = it.get("id") as Int,
                userName = it.get("name") as String,
                userPassword = it.get("password") as String,
                userPhoneNumber = it.get("phoneNumber") as Int
            )
        }
        db.collection("users").document(userPhoneNumber.toString()).get().addOnFailureListener {
            user = UserModel(
                userId = null,
                userName = null,
                userPassword = null,
                userPhoneNumber = null
            )
        }
        return user
    }
}
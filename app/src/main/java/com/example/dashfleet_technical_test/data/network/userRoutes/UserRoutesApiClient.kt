package com.example.dashfleet_technical_test.data.network.userRoutes

import com.example.dashfleet_technical_test.core.constants.FirestoreConstants
import com.example.dashfleet_technical_test.data.model.userRoutes.RouteModel
import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRoutesApiClient @Inject constructor(
    private val db: FirebaseFirestore
) {
    fun getUserRoutes(userPhoneNumber: String): Observable<UserRoutes> {


        return Observable.create { emitter ->
            val listenerRegistration = db
                .collection(FirestoreConstants.USER_ROUTES)
                .document(userPhoneNumber)
                .addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                    }
                    if (snapshot != null && snapshot.exists()) {
                        val userRoutes = snapshot.toObject(UserRoutes::class.java)!!
                        emitter.onNext(userRoutes)
                    }
                })
            emitter.setCancellable { listenerRegistration.remove() }
        }

//        return try {
//            val userRoutes =
//                db.collection(FirestoreConstants.USER_ROUTES).document(userPhoneNumber).get()
//                    .await().toObject(UserRoutesModel::class.java)
//
//            UserRoutesModel(
//                routes = userRoutes?.routes,
//                userId = userRoutes?.userId
//            )
//        } catch (e: Exception) {
//            UserRoutesModel()
//        }
    }
}
package com.example.dashfleet_technical_test.domain.userRoutes

import com.example.dashfleet_technical_test.data.repository.userRoutes.UserRoutesRepository
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserRoutesFirestoreUseCase @Inject constructor(
    private val repository: UserRoutesRepository
) {
    operator fun invoke(userPhoneNumber: String): Observable<UserRoutes> {
        return repository.getUserRouteFirestore(
            userPhoneNumber = userPhoneNumber
        )
    }
}
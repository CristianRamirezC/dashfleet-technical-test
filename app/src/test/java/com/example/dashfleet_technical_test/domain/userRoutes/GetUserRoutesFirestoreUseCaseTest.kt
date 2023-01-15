package com.example.dashfleet_technical_test.domain.userRoutes

import com.example.dashfleet_technical_test.data.repository.userRoutes.UserRoutesRepository
import com.example.dashfleet_technical_test.domain.model.userRoutes.Route
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserRoutesFirestoreUseCaseTest {

    @RelaxedMockK
    private lateinit var userRoutesRepository: UserRoutesRepository

    private lateinit var getUserRoutesUseCase: GetUserRoutesFirestoreUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getUserRoutesUseCase = GetUserRoutesFirestoreUseCase(userRoutesRepository)
    }

    @Test
    fun `when the api returns a UserRoutes object then the use case returns it`()
            : Unit = runBlocking {
        //Given
        val phoneNumber: String = "3136756531"
        val userRoute: UserRoutes = UserRoutes(
            routes = listOf(
                Route(
                    routeName = "Ruta 1",
                    routeDistance = 10
                ),
                Route(
                    routeName = "Ruta 2",
                    routeDistance = 15
                )
            )
        )

        coEvery {
            userRoutesRepository.getUserRouteFirestore(phoneNumber)
        } returns userRoute

        //When
        val useCaseResponse = getUserRoutesUseCase(phoneNumber)

        //Then

        coVerify(exactly = 1) {
            userRoutesRepository.getUserRouteFirestore(phoneNumber)
        }
        assert(useCaseResponse == userRoute)
    }

    @Test
    fun `when the user does not have routes, the api returns an UserRoutesModel with empty info then the use case returns the object`()
            : Unit = runBlocking {

        //Given
        val phoneNumber: String = "3136756531"
        val userRoute: UserRoutes = UserRoutes(
            routes = emptyList(),
            userId = 0L
        )

        coEvery {
            userRoutesRepository.getUserRouteFirestore(phoneNumber)
        } returns userRoute

        //When
        val useCaseResponse = getUserRoutesUseCase(phoneNumber)

        //Then
        coVerify(exactly = 1) {
            userRoutesRepository.getUserRouteFirestore(phoneNumber)
        }
        assert(useCaseResponse == userRoute)
    }
}
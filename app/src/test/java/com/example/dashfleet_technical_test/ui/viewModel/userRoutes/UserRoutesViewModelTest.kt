package com.example.dashfleet_technical_test.ui.viewModel.userRoutes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dashfleet_technical_test.domain.model.userRoutes.Route
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import com.example.dashfleet_technical_test.domain.userRoutes.GetUserRoutesFirestoreUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class UserRoutesViewModelTest {

    private lateinit var userRoutesViewModel: UserRoutesViewModel

    @RelaxedMockK
    private lateinit var getUserRoutesFirestoreUseCase: GetUserRoutesFirestoreUseCase

    //Rule for "tricking" the dispatcher in order to test coroutines
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        userRoutesViewModel = UserRoutesViewModel(getUserRoutesFirestoreUseCase)

        //Tricking the main thread to test coroutines
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter() {
        //Resetting the main thread
        Dispatchers.resetMain()
    }

    @Test
    fun `when the api returns a valid UserRoutes object then set them on voiewModel`()
            : Unit = runTest {
        //Given
        val phoneNumber: String = "3136756531"
        val userRoutes = UserRoutes(
            routes = listOf(
                Route(
                    routeName = "Ruta 1",
                    routeDistance = 3
                )
            ),
            userId = 1L
        )

        coEvery {
            getUserRoutesFirestoreUseCase(phoneNumber)
        } returns userRoutes

        //When
        userRoutesViewModel.getUserRoutes(phoneNumber)

        //Then

        assert(userRoutesViewModel.userRoutes.value == userRoutes)

    }


}
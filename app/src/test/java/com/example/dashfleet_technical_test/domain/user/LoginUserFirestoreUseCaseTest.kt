package com.example.dashfleet_technical_test.domain.user

import com.example.dashfleet_technical_test.data.repository.user.UserRepository
import com.example.dashfleet_technical_test.domain.model.user.UserLoginResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class LoginUserFirestoreUseCaseTest {

    @RelaxedMockK
    private lateinit var loginUserRepository: UserRepository
    private lateinit var loginUserFireStoreUseCase: LoginUserFirestoreUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        loginUserFireStoreUseCase = LoginUserFirestoreUseCase(loginUserRepository)
    }

    @Test
    fun `when the user exists and the API returns a valid UserLoginModel object then the use case returns it`()
            : Unit = runBlocking {
        //Given
        val phoneNumber: String = "3136756531"
        val password: String = "1"
        val userLoginResponse = UserLoginResponse(
            userId = 1,
            userName = "Cristian Ramirez",
            userPhoneNumber = "3136756531",
            ableToLogin = true,
            connectionError = false
        )
        coEvery {
            loginUserRepository.loginUserFirestore(phoneNumber, password)
        } returns userLoginResponse

        //When
        val useCaseResponse = loginUserFireStoreUseCase(phoneNumber, password)

        //Then

        coVerify(exactly = 1) {
            loginUserRepository.loginUserFirestore(phoneNumber, password)
        }
        assert(useCaseResponse == userLoginResponse)
    }

    @Test
    fun `when the user does not exist or there is a network connection error api returns UserLoginResponse object with nulls and then the use case return that object`()
            : Unit = runBlocking {

        //Given
        val phoneNumber: String = "3136756531"
        val password: String = "1"
        val userLoginResponse = UserLoginResponse(
            userId = null,
            userName = null,
            userPhoneNumber = null,
            ableToLogin = false,
        )

        coEvery {
            loginUserRepository.loginUserFirestore(phoneNumber, password)
        } returns userLoginResponse

        //When
        val useCaseResponse = loginUserFireStoreUseCase(phoneNumber, password)

        //Then
        coVerify(exactly = 1) {
            loginUserRepository.loginUserFirestore(phoneNumber, password)
        }
        assert(useCaseResponse == userLoginResponse)
    }
}

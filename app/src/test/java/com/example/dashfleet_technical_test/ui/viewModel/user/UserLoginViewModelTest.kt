package com.example.dashfleet_technical_test.ui.viewModel.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dashfleet_technical_test.domain.model.user.UserLoginResponse
import com.example.dashfleet_technical_test.domain.user.LoginUserFirestoreUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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
class UserLoginViewModelTest {

    private lateinit var userLoginViewModel: UserLoginViewModel

    @RelaxedMockK
    private lateinit var loginUserUseCase: LoginUserFirestoreUseCase

    //Rule for "tricking" the dispatcher in order to test coroutines
    @get:Rule
    var mainDispatcherRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        userLoginViewModel = UserLoginViewModel(loginUserUseCase)

        //Tricking the main thread to test coroutines
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        //Resetting the main thread
        Dispatchers.resetMain()
    }

    @Test
    fun `when loginUser is executed and no users are found then set errorLogging to true`()
            : Unit = runTest {
        //Given
        val userLoginResponse: UserLoginResponse = UserLoginResponse(
            userId = null,
            userName = null,
            userPhoneNumber = null,
            ableToLogin = false
        )

        val phoneNumber: String = "3136756531"
        val password: String = "2"

        coEvery {
            loginUserUseCase(phoneNumber, password)
        } returns userLoginResponse

        //When
        userLoginViewModel.loginUser(phoneNumber, password)

        //Then
        assert(userLoginViewModel.isErrorLogging.value == true)
        assert(userLoginViewModel.userAbleToLogin.value == false)
        coVerify(exactly = 1) {
            loginUserUseCase(phoneNumber, password)
        }
    }

    @Test
    fun `when the login info is correct then set the user values and able to login to true`()
            : Unit = runTest {
        //Given
        val phoneNumber: String = "3136756531"
        val password: String = "1"
        val userLoginResponse: UserLoginResponse = UserLoginResponse(
            userId = 1,
            userName = "Cristian Ramirez",
            userPhoneNumber = "3136756531",
            ableToLogin = true
        )

        coEvery {
            loginUserUseCase(phoneNumber, password)
        } returns userLoginResponse

        //When
        userLoginViewModel.loginUser(phoneNumber, password)

        //Then

        assert(userLoginViewModel.userId.value == userLoginResponse.userId)
        assert(userLoginViewModel.userName.value == userLoginResponse.userName)
        assert(userLoginViewModel.userAbleToLogin.value == userLoginResponse.ableToLogin)
        assert(userLoginViewModel.userPhoneNumber.value == userLoginResponse.userPhoneNumber)
        assert(userLoginViewModel.isLoading.value == false)
        coVerify(exactly = 1) {
            loginUserUseCase(phoneNumber, password)
        }
    }

    @Test
    fun `when logoutUser is executed then reset the values of user`(): Unit =
        runTest {
            //Given

            //When
            userLoginViewModel.logoutUser()

            //Then
            assert(userLoginViewModel.userName.value == "")
            assert(userLoginViewModel.userId.value == 0L)
            assert(userLoginViewModel.userAbleToLogin.value == false)
            assert(userLoginViewModel.userPhoneNumber.value == "")
        }

}
package com.example.dashfleet_technical_test.ui.viewModel.user

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashfleet_technical_test.domain.model.user.UserLoginResponse
import com.example.dashfleet_technical_test.domain.user.LoginUserFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserFirestoreUseCase
) : ViewModel() {

    private var _userLoginResponse = MutableLiveData<UserLoginResponse>()
    val userLoginResponse: LiveData<UserLoginResponse> = _userLoginResponse

    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = Transformations.map(_userLoginResponse) {
        it.userName
    }

    private var _userId = MutableLiveData<Long>()
    val userId: LiveData<Long> = Transformations.map(_userLoginResponse) {
        it.userId
    }

    private var _userAbleToLogin = MutableLiveData<Boolean>()
    val userAbleToLogin: LiveData<Boolean> = Transformations.map(_userLoginResponse) {
        it.ableToLogin
    }

    private var _userPhoneNumber = MutableLiveData<String>()
    val userPhoneNumber: LiveData<String> = Transformations.map(_userLoginResponse) {
        it.userPhoneNumber
    }

    private var _userLoginPhoneNumber = MutableLiveData<String>()
    val userLoginPhoneNumber: LiveData<String> = _userLoginPhoneNumber

    private var _userLoginPassword = MutableLiveData<String>()
    val userLoginPassword: LiveData<String> = _userLoginPassword

    private var _isLoginButtonEnabled = MutableLiveData<Boolean>()
    val isLoginButtonEnabled: LiveData<Boolean> = _isLoginButtonEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isErrorLogging = MutableLiveData<Boolean>()
    val isErrorLogging: LiveData<Boolean> = _isErrorLogging


    fun loginUser(userPhoneNumber: String, userPassword: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val user: UserLoginResponse =
                loginUserUseCase(userPhoneNumber, userPassword)
            _userLoginPassword.postValue("")

            if (
                user.userId == null &&
                user.userName == null &&
                user.userPhoneNumber == null &&
                !user.ableToLogin
            ) {
                _isErrorLogging.postValue(true)
                _userAbleToLogin.postValue(false)
            } else {
                _userLoginResponse.postValue(user)
                _isErrorLogging.postValue(false)
            }
            _isLoading.postValue(false)
        }
    }

    fun logoutUser() {
        _isLoading.postValue(true)

        _userLoginResponse.postValue(
            UserLoginResponse(
                userId = 0L,
                userName = "",
                ableToLogin = false,
                userPhoneNumber = ""
            )
        )

        _isLoading.postValue(false)

    }

    fun onLoginChanged(userPhoneNumber: String, userPassword: String) {
        _userLoginPhoneNumber.value = userPhoneNumber
        _userLoginPassword.value = userPassword

        _isLoginButtonEnabled.value = enableLoginButton(userPhoneNumber, userPassword)

    }

    private fun enableLoginButton(userPhoneNumber: String, userPassword: String) =
        Patterns.PHONE.matcher(userPhoneNumber).matches() && userPassword.isNotEmpty()
}
package com.example.dashfleet_technical_test.ui.viewModel.user

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private var _userPhoneNumber = MutableLiveData<String>()
    val userPhoneNumber: LiveData<String> = _userPhoneNumber

    private var _userLoginPhoneNumber = MutableLiveData<String>()
    val userLoginPhoneNumber: LiveData<String> = _userLoginPhoneNumber

    private var _userId = MutableLiveData<Long>()
    val userId: LiveData<Long> = _userId

    private var _userAbleToLogin = MutableLiveData<Boolean>()
    val userAbleToLogin: LiveData<Boolean> = _userAbleToLogin

    private var _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String> = _userPassword

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
            _userPassword.postValue("")

            if (
                user.userId == null &&
                user.userName == null &&
                user.userPhoneNumber == null &&
                !user.ableToLogin
            ) {
                _isErrorLogging.postValue(true)
                _userAbleToLogin.postValue(false)
            } else {
                _userName.postValue(user.userName)
                _userId.postValue(user.userId)
                _userAbleToLogin.postValue(user.ableToLogin)
                _userPhoneNumber.postValue(user.userPhoneNumber)
                _isErrorLogging.postValue(false)
            }
            _isLoading.postValue(false)
        }
    }

    fun logoutUser() {
        _isLoading.postValue(true)

        _userName.postValue("")
        _userId.postValue(0L)
        _userAbleToLogin.postValue(false)
        _userPhoneNumber.postValue("")
        _userLoginPhoneNumber.postValue("")

        _isLoading.postValue(false)

    }

    fun onLoginChanged(userPhoneNumber: String, userPassword: String) {
        _userLoginPhoneNumber.value = userPhoneNumber
        _userPassword.value = userPassword

        _isLoginButtonEnabled.value = enableLoginButton(userPhoneNumber, userPassword)

    }

    private fun enableLoginButton(userPhoneNumber: String, userPassword: String) =
        Patterns.PHONE.matcher(userPhoneNumber).matches() && userPassword.isNotEmpty()
}
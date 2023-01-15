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
    private val loginUserCase: LoginUserFirestoreUseCase
) : ViewModel() {
    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private var _userPhoneNumber = MutableLiveData<String>()
    val userPhoneNumber: LiveData<String> = _userPhoneNumber

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


    fun loginUser() {
        viewModelScope.launch {
            val user: UserLoginResponse =
                loginUserCase(userPhoneNumber.value!!, userPassword.value!!)
            _userName.postValue(user.userName)
            _userId.postValue(user.userId)
            _userAbleToLogin.postValue(user.ableToLogin)
            _userPhoneNumber.postValue(user.userPhoneNumber)
        }
    }

    fun onLoginChanged(userPhoneNumber: String, userPassword: String) {
        _userPhoneNumber.value = userPhoneNumber
        _userPassword.value = userPassword

        _isLoginButtonEnabled.value = enableLoginButton(userPhoneNumber, userPassword)

    }

    fun enableLoginButton(userPhoneNumber: String, userPassword: String) =
        Patterns.PHONE.matcher(userPhoneNumber).matches() && userPassword.isNotEmpty()
}
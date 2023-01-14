package com.example.dashfleet_technical_test.ui.viewModel.user

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
class UserViewModel @Inject constructor(
    private val loginUserCase: LoginUserFirestoreUseCase
) : ViewModel() {
    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun loginUser(userPhoneNumber: String, userPassword: String) {
        viewModelScope.launch {
            val user: UserLoginResponse = loginUserCase(userPhoneNumber, userPassword)
            _userName.postValue(user.userName)
        }
    }
}
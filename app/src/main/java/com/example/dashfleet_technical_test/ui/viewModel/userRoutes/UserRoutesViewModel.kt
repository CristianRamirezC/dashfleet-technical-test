package com.example.dashfleet_technical_test.ui.viewModel.userRoutes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import com.example.dashfleet_technical_test.domain.userRoutes.GetUserRoutesFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRoutesViewModel @Inject constructor(
    private val getUserRoutesFirestore: GetUserRoutesFirestoreUseCase
) : ViewModel() {
    private var _userRoutes = MutableLiveData<UserRoutes>()
    val userRoutes: LiveData<UserRoutes> = _userRoutes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getUserRoutes(userPhoneNumber: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val userRoutes: UserRoutes = getUserRoutesFirestore(userPhoneNumber)
            _userRoutes.postValue(userRoutes)
            _isLoading.postValue(false)
        }
    }
}
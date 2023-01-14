package com.example.dashfleet_technical_test.ui.viewModel.userRoutes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashfleet_technical_test.data.model.userRoutes.UserRoutesModel
import com.example.dashfleet_technical_test.domain.userRoutes.userRoutes.GetUserRoutesFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRoutesViewModel @Inject constructor(
    private val getUserRoutesFirestore: GetUserRoutesFirestoreUseCase
) : ViewModel() {
    private var _userRoutes = MutableLiveData<UserRoutesModel>()
    val userRoutes: LiveData<UserRoutesModel> = _userRoutes


    fun getUserRoutes(userPhoneNumber: String) {
        viewModelScope.launch {
            val userRoutes = getUserRoutesFirestore(userPhoneNumber)
            _userRoutes.postValue(userRoutes)
        }
    }
}
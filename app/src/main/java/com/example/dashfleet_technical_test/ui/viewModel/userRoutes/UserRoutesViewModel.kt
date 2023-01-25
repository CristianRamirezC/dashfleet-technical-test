package com.example.dashfleet_technical_test.ui.viewModel.userRoutes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashfleet_technical_test.domain.model.userRoutes.UserRoutes
import com.example.dashfleet_technical_test.domain.userRoutes.GetUserRoutesFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRoutesViewModel @Inject constructor(
    private val getUserRoutesFirestoreUseCase: GetUserRoutesFirestoreUseCase
) : ViewModel() {
    private var _userRoutes = MutableLiveData<UserRoutes>()
    val userRoutes: LiveData<UserRoutes> = _userRoutes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getUserRoutes(userPhoneNumber: String) {

        val userRoutesObservable = getUserRoutesFirestoreUseCase(userPhoneNumber)

        userRoutesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

//        viewModelScope.launch {
//            _isLoading.postValue(true)
//            val userRoutes: UserRoutes = getUserRoutesFirestoreUseCase(userPhoneNumber)
//            _userRoutes.postValue(userRoutes)
//            _isLoading.postValue(false)
//        }

    }
}
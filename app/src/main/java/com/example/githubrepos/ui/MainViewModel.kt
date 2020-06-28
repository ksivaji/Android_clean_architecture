package com.example.githubrepos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazonaws.mobile.client.UserState
import com.example.githubrepos.api.Result
import com.example.githubrepos.repository.GithubRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val repository: GithubRepository) : ViewModel() {

    private var _userState = MutableLiveData<Result<UserState?>>()
    val userState: LiveData<Result<UserState?>> = _userState

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        userName.value = ""
        password.value = ""
        viewModelScope.launch {
            updateUserState()
        }
    }

    fun onLogoutClick() {
        setStateToLoading()
        viewModelScope.launch {
//            delay(1000)
            repository.doSignOut()
            updateUserState()
        }
    }

    fun onLoginClick() {
        setStateToLoading()
        viewModelScope.launch {
//            delay(1000)
            val result = repository.doSignIn(userName.value!!, password.value!!)
            updateUserState()
            /*when (result.status) {
                Result.Status.LOADING -> userState.value = Result.loading()
                Result.Status.SUCCESS -> userState.value = Result.success(UserState.SIGNED_IN)
                Result.Status.ERROR -> userState.value = Result.success(UserState.SIGNED_OUT)
            }*/
        }
    }

    private suspend fun updateUserState() {
        _userState.value = repository.getUserStateDetails()
    }

    private fun setStateToLoading() {
        _userState.value = Result.loading()
    }

}
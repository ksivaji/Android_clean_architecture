package com.example.githubrepos.ui.users

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.githubrepos.data.GithubUser
import com.example.githubrepos.repository.GithubRepository
import com.example.githubrepos.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private val _userList = MutableLiveData<PagedList<GithubUser>>()
    lateinit var userList : LiveData<PagedList<GithubUser>> //= _userList
    internal val selectItemEvent = SingleLiveEvent<GithubUser>()

    fun getUsers(){
        viewModelScope.launch {
            userList = repository.observePagedUserList()
        }

        liveData (Dispatchers.IO) {
            val  userList1 = repository.observePagedUserList()
            emit(userList1)
        }
    }

}
package com.example.githubrepos.ui.users.repo

import androidx.lifecycle.*
import com.example.githubrepos.data.GithubUser
import com.example.githubrepos.data.GithubUserWithRepos
import com.example.githubrepos.repository.GithubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepoViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {



    fun getUserRepos(user:GithubUser) {
        viewModelScope.launch {
            _userRepos.value = repository.getUserRepos(user.userId)
        }
    }



}
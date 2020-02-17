package com.example.githubrepos.ui

import androidx.lifecycle.ViewModel
import com.example.githubrepos.repository.GithubRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    val userList = repository.observePagedUserList()

}
package com.example.githubrepos.data

import com.example.githubrepos.api.BaseDataSource
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.api.Result
import timber.log.Timber
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val service: GithubService,
    private val dao: GithubUserDao
) :
    BaseDataSource() {

    suspend fun fetchUsers(itemId: Int) {
        Timber.i("koya Making network request for data")
        val response = getResult { service.getUsers(itemId) }
        when (response.status) {
            Result.Status.SUCCESS -> {
                Timber.i("koya got results. Inserting to Database: size -> ${response.data!!.size}")
                dao.insertUsers(response.data!!)
            }
            Result.Status.ERROR -> {
                Timber.e("Network error while loading results")
            }
            Result.Status.LOADING -> {
                Timber.e("loading results ...")
            }
        }
    }

}
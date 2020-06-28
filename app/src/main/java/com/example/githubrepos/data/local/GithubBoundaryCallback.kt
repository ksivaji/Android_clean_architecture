package com.example.githubrepos.data.local

import androidx.paging.PagedList
import com.example.githubrepos.data.GithubRemoteDataSource
import com.example.githubrepos.data.GithubUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class GithubBoundaryCallback(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val scope: CoroutineScope
) : PagedList.BoundaryCallback<GithubUser>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Timber.i("koya Loading initial data")
        scope.launch {
            githubRemoteDataSource.fetchUsers(0)
        }
    }


    override fun onItemAtEndLoaded(itemAtEnd: GithubUser) {
        super.onItemAtEndLoaded(itemAtEnd)
        Timber.i("koya Loading more data")
        scope.launch {
            githubRemoteDataSource.fetchUsers(itemAtEnd.id)
        }
    }

}
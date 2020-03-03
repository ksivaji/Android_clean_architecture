package com.example.githubrepos.data

import androidx.paging.PagedList
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
        Timber.i("koya Loading more data: useerId: ${itemAtEnd.userId}")
        scope.launch {
            githubRemoteDataSource.fetchUsers(itemAtEnd.userId)
        }
    }

}
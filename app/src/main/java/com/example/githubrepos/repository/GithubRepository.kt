package com.example.githubrepos.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.githubrepos.data.*
import com.example.githubrepos.di.CoroutineScropeIO
import com.example.githubrepos.utils.Constanst.Companion.PAGE_SIZE
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val dao: GithubUserDao,
    private val githubRemoteDataSource: GithubRemoteDataSource,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) {

    fun observePagedUserList(): LiveData<PagedList<GithubUser>> {

        return dao.getUsers().toLiveData(
            PAGE_SIZE,
            boundaryCallback = GithubBoundaryCallback(githubRemoteDataSource, ioCoroutineScope)
        )
    }


    suspend fun getUserRepos(userId: Int): List<GithubUserWithRepos> {
        return dao.getUsersWithRepos(userId)
    }

}
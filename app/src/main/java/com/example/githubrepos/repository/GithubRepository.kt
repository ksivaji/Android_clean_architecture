package com.example.githubrepos.repository

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.githubrepos.data.GithubBoundaryCallback
import com.example.githubrepos.data.GithubRemoteDataSource
import com.example.githubrepos.data.GithubUser
import com.example.githubrepos.data.GithubUserDao
import com.example.githubrepos.di.CoroutineScropeIO
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

}
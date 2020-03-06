package com.example.githubrepos.repository

import android.app.Application
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserState
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobile.client.results.SignInResult
import com.example.githubrepos.api.Result
import com.example.githubrepos.data.GithubBoundaryCallback
import com.example.githubrepos.data.GithubRemoteDataSource
import com.example.githubrepos.data.GithubUser
import com.example.githubrepos.data.GithubUserDao
import com.example.githubrepos.di.CoroutineScropeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class GithubRepository @Inject constructor(
    private val application: Application,
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


    //Providing Main Safety for all repository methods by switching context to IO Dispatcher
    suspend fun getUserStateDetails(): Result<UserState?> =
        withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<Result<UserState?>> {
                AWSMobileClient.getInstance()
                    .initialize(
                        application.applicationContext,
                        object : Callback<UserStateDetails?> {
                            override fun onResult(userStateDetails: UserStateDetails?) {
                                it.resume(Result.success(userStateDetails!!.userState))
                            }

                            override fun onError(e: Exception?) {
                                it.resume(Result.error("Error while getting UserSate"))
                            }
                        })
            }
        }

    suspend fun doSignOut() {
        withContext(Dispatchers.IO) {
            AWSMobileClient.getInstance().signOut()
        }
    }

    suspend fun doSignIn(userName: String, password: String): Result<SignInResult?> =
        withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<Result<SignInResult?>> {
                AWSMobileClient.getInstance()
                    .signIn(userName, password, null, object : Callback<SignInResult> {
                        override fun onResult(result: SignInResult?) {
                            it.resume(Result.success(result!!))
                        }

                        override fun onError(e: java.lang.Exception?) {
                            it.resume(Result.error("Error while Sign-in process"))
                        }
                    })
            }
        }

}
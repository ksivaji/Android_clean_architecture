package com.example.githubrepos.api

import com.example.githubrepos.data.GithubRepo
import com.example.githubrepos.data.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubService {

    companion object {
        const val ENDPOINT = "https://api.github.com/"
    }

    @GET("users")
    @Headers("Authorization: token cff31aff3969cef7f36c9df4a82f8808b423a087")
    suspend fun getUsers(
        @Query("since") lastItemId: Int?
    ): Response<List<GithubUser>>

    @Headers("Authorization:  token cff31aff3969cef7f36c9df4a82f8808b423a087")
    @GET("users/{userName}/repos")
    suspend fun getUserRepos(
        @Path("userName") userName: String
    ): Response<List<GithubRepo>>
}

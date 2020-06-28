package com.example.githubrepos.api

import com.example.githubrepos.data.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubService {

    companion object {
        const val ENDPOINT = "https://api.github.com/"
    }

    @GET("users")
    suspend fun getUsers(
        @Query("since") lastItemId: Int?
    ): Response<List<GithubUser>>
}
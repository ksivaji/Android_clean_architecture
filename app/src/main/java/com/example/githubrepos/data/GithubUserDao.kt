package com.example.githubrepos.data

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM users")
     fun getUsers(): DataSource.Factory<Int, GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<GithubUser>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRepos(userRepos: List<GithubRepo>)

    @Transaction
    @Query("SELECT * FROM users WHERE  userId = :userId")
    suspend fun getUsersWithRepos(userId: Int): List<GithubUserWithRepos>


}
package com.example.githubrepos.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): DataSource.Factory<Int, GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<GithubUser>)

}
package com.example.githubrepos.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepos.data.GithubUser

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): DataSource.Factory<Int, GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<GithubUser>)

}
package com.example.githubrepos.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity (tableName = "users")
data class GithubUser(
    @PrimaryKey
    @field:SerializedName("id")
    val id :Int,
    @field:SerializedName("login")
    val name: String,
    @field:SerializedName("repos_url")
    val repoUrl :String,
    @field:SerializedName("avatar_url")
    val imageUrl: String )


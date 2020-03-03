package com.example.githubrepos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class GithubRepo(

    @PrimaryKey
    var repoId: Int,
    var repoUserId: Int,
    var repoName: String,
    val repoUrl :String


)
package com.example.githubrepos.data

import androidx.room.Embedded
import androidx.room.Relation

data class GithubUserWithRepos(
    @Embedded val user: GithubUser,

    @Relation(
        parentColumn = "userId",
        entityColumn = "repoUserId"
    )
    val repoList: List<GithubRepo>
)
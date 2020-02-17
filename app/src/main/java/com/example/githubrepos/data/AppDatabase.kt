package com.example.githubrepos.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [GithubUser::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun githubUserDao(): GithubUserDao


}
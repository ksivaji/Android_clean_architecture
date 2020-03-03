package com.example.githubrepos.di

import com.example.githubrepos.ui.users.UserListActivity
import com.example.githubrepos.ui.users.repo.UserRepoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract fun contributeUserListActivity(): UserListActivity

    @ContributesAndroidInjector()
    abstract fun contributeUserRepoActivity(): UserRepoActivity
}
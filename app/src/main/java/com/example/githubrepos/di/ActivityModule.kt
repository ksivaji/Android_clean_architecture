package com.example.githubrepos.di

import com.example.githubrepos.ui.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract fun contributeUserListActivity() : UserListActivity
}
package com.example.githubrepos.data

import androidx.paging.PagedList


class GithubPageDataSourceFactory {


    companion object {
        private const val PAGE_SIZE = 10

        fun pageListConfig() = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()
    }
}
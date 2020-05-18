package com.hh.gitbub.di.module

import com.hh.gitbub.ui.activity.RepoListActivity
import com.hh.gitbub.ui.adapter.RepositoryAdapter
import com.hh.gitbub.viewmodel.RepositoryListViewModel
import dagger.Module
import dagger.Provides

@Module
class RepoListModule {

    @Provides
    fun getRepositoryViewModel(): RepositoryListViewModel {
        return RepositoryListViewModel()
    }

    @Provides
    fun getRepositoryAdapter() : RepositoryAdapter{
        return RepositoryAdapter()
    }
}
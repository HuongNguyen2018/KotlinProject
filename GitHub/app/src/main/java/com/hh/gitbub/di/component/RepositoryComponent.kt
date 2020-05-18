package com.hh.gitbub.di.component

import com.hh.gitbub.di.module.RepoListModule
import com.hh.gitbub.ui.activity.RepoListActivity
import com.hh.gitbub.viewmodel.RepositoryListViewModel
import dagger.Component

@Component(modules = arrayOf(RepoListModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface RepositoryComponent {
    fun inject(viewModel: RepositoryListViewModel)
    fun injectActivity(repoListActivity: RepoListActivity)
}
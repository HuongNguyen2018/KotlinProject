package com.hh.gitbub.di.component

import androidx.annotation.LayoutRes
import com.hh.gitbub.di.module.UserAdapterModule
import com.hh.gitbub.di.module.UserListModule
import com.hh.gitbub.ui.activity.UserListActivity
import com.hh.gitbub.viewmodel.UserListViewModel
import dagger.BindsInstance
import dagger.Component


@Component(modules = arrayOf(UserListModule::class, UserAdapterModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface UserComponent {

    fun injectData(userListViewModel: UserListViewModel)
    fun inject(activity: UserListActivity)

}
package com.hh.gitbub.di.module

import androidx.lifecycle.ViewModelProvider
import com.hh.gitbub.ui.activity.UserListActivity
import com.hh.gitbub.viewmodel.UserListViewModel
import dagger.Module
import dagger.Provides

@Module
class UserListModule {

    private var mUserListActivity: UserListActivity

    constructor(userListActivity: UserListActivity) {
        mUserListActivity = userListActivity
    }

    @Provides
    fun getUserListActivity() = mUserListActivity

    @Provides
    fun getUserListViewModel(): UserListViewModel {
        return ViewModelProvider(mUserListActivity).get(UserListViewModel::class.java)
    }

}
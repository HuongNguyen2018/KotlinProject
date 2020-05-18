package com.hh.gitbub.di.module

import androidx.annotation.LayoutRes
import com.hh.gitbub.data.model.Repository
import com.hh.gitbub.data.model.User
import com.hh.gitbub.di.annotation.ActivityContext
import com.hh.gitbub.ui.activity.RepoListActivity
import com.hh.gitbub.ui.activity.UserListActivity
import com.hh.gitbub.ui.adapter.RecyclerViewAdapter
import dagger.Module
import dagger.Provides

@Module (includes = arrayOf(UserListModule::class))
class UserAdapterModule {

    private var mRecyclerViewAdapter: RecyclerViewAdapter? = null

    @Provides
    fun getRecyclerViewAdapter( clickListener: RecyclerViewAdapter.AdapterClickListener<User>) : RecyclerViewAdapter {
        return RecyclerViewAdapter(clickListener)

    }

    @Provides
    fun getClickListenerUserApdater(activity: UserListActivity) : RecyclerViewAdapter.AdapterClickListener<User> {
        return activity

    }

}
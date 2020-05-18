package com.hh.gitbub.di.module

import android.content.Context
import com.hh.gitbub.di.annotation.ActivityContext
import com.hh.gitbub.di.annotation.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ContextModule {

    private var mContextApplication: Context? = null
    private var mContextActivity : Context? = null

    constructor(applicationContext: Context){
        mContextApplication = applicationContext
    }
    constructor(contextApplication: Context,  contextActivity: Context) {
        mContextActivity = contextActivity
        mContextApplication = contextApplication
    }

    @Provides
    @ApplicationContext
    fun getContextApplication() = mContextApplication

    @Provides
    @ActivityContext
    fun getContextActivity() = mContextActivity
}
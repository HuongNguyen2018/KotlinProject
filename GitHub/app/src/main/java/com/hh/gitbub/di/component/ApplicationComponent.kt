package com.hh.gitbub.di.component

import android.app.Application
import com.hh.gitbub.data.api.ApiService
import com.hh.gitbub.di.module.ContextModule
import com.hh.gitbub.di.module.RetrofitModule
import dagger.Component


@Component(modules = arrayOf(ContextModule::class, RetrofitModule::class))
interface ApplicationComponent {


    fun getApiService() : ApiService

    fun inject(application: Application)


}
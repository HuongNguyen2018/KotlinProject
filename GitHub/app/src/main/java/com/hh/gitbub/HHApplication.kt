package com.hh.gitbub

import android.app.Application
import com.hh.gitbub.di.component.ApplicationComponent
import com.hh.gitbub.di.component.DaggerApplicationComponent
import com.hh.gitbub.di.module.ContextModule

class HHApplication : Application() {
    companion object {
        lateinit var component : ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this.applicationContext))
            .build()

        component.inject(this)

    }



}
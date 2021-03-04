package com.rafaelfv.improvingtest.application

import android.app.Application
import android.content.Context
import com.rafaelfv.improvingtest.injection.component.ComponentlInjector
import com.rafaelfv.improvingtest.injection.component.DaggerComponentlInjector
import com.rafaelfv.improvingtest.injection.module.NetworkModule

class MyApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var component: ComponentlInjector
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        component = DaggerComponentlInjector.builder().networkModule(NetworkModule).build()
    }
}
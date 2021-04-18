package com.example.arch

import android.app.Application
import com.example.arch.di.app.AppComponent
import com.example.arch.di.app.AppModule
import com.example.arch.di.app.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        Timber.d("onCreate")
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}

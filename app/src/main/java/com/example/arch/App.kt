package com.example.arch

import android.app.Application
import com.example.arch.di.AppCompositionRoot
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        Timber.d("onCreate")
        appCompositionRoot = AppCompositionRoot(this)
    }
}

package com.example.arch.di.app

import com.example.arch.di.activity.ActivityComponent
import com.example.arch.di.activity.ActivityModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        ServiceModule::class,
    ]
) @Singleton interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

}

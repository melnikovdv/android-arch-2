package com.example.arch.di.app

import android.app.Application
import com.example.arch.di.activity.ActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        AppModule.Binds::class,
    ]
) @Singleton interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder

    @Component.Builder interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }
}

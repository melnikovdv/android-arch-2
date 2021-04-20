package com.example.arch.di.activity

import android.app.Activity
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.arch.screen.common.base.BaseActivity
import com.example.arch.screen.common.nav.BackPressDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module @InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides fun layoutInflater(baseActivity: BaseActivity) =
        LayoutInflater.from(baseActivity)

    @Provides @ActivityScoped fun fragmentManager(baseActivity: BaseActivity): FragmentManager =
        baseActivity.supportFragmentManager

    @Provides @ActivityScoped fun baseActivity(activity: Activity): BaseActivity =
        activity as BaseActivity

    @Module @InstallIn(ActivityComponent::class) interface Binds {
        @dagger.Binds fun backPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher
    }
}

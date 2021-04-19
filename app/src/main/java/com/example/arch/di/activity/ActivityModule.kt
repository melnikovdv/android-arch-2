package com.example.arch.di.activity

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.arch.screen.common.base.BaseActivity
import com.example.arch.screen.common.nav.BackPressDispatcher
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {
    @Provides @ActivityScope fun layoutInflater(baseActivity: BaseActivity) =
        LayoutInflater.from(baseActivity)

    @Provides @ActivityScope fun fragmentManager(baseActivity: BaseActivity): FragmentManager =
        baseActivity.supportFragmentManager

    @Module interface Binds {
        @dagger.Binds fun backPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher
    }
}

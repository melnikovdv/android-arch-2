package com.example.arch.di.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.arch.screen.common.base.BaseActivity
import com.example.arch.screen.common.nav.BackPressDispatcher
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: BaseActivity,
    val savedInstanceState: Bundle?
) {
    @Provides @ActivityScope fun activity(): BaseActivity = activity

    @Provides fun savedInstanceState(): Bundle? = savedInstanceState

    @Provides @ActivityScope fun layoutInflater(baseActivity: BaseActivity) =
        LayoutInflater.from(baseActivity)

    @Provides @ActivityScope fun fragmentManager(baseActivity: BaseActivity): FragmentManager =
        baseActivity.supportFragmentManager

    @Module interface Binds {
        @dagger.Binds fun backPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher
    }
}

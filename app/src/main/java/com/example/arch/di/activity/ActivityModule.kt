package com.example.arch.di.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.arch.di.app.AppComponent
import com.example.arch.screen.common.base.BaseActivity
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: BaseActivity,
    private val appComponent: AppComponent,
    val savedInstanceState: Bundle?
) {
    @Provides fun application() = appComponent.application()

    @Provides @ActivityScope fun layoutInflater() = LayoutInflater.from(activity)

    @Provides @ActivityScope fun fragmentManager(): FragmentManager =
        activity.supportFragmentManager

    @Provides @ActivityScope fun screenNavigator(
        fragmentManager: FragmentManager
    ) = ScreenNavigator(fragmentManager, savedInstanceState)

    @Provides fun findBlogItemService() = appComponent.findBlogItemService()

    @Provides fun refreshViewsAndVotesService() = appComponent.refreshViewsAndVotesService()

    @Provides fun backPressDispatcher(): BackPressDispatcher = activity
}

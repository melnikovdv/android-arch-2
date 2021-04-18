package com.example.arch.di.presentation

import android.view.LayoutInflater
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.di.activity.ActivityComponent
import com.example.arch.screen.common.mvp.factory.MvpViewFactory
import com.example.arch.screen.common.mvp.factory.PresenterFactory
import com.example.arch.screen.common.mvvm.ViewModelFactory
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(
    private val activityComponent: ActivityComponent
) {
    @Provides fun activityComponent(): ActivityComponent = activityComponent

    @Provides fun layoutInflater() = activityComponent.layoutInflater()

    @Provides fun backPressDispatcher() = activityComponent.backPressDispatcher()

    @Provides fun mvpViewFactory(
        layoutInflater: LayoutInflater
    ) = MvpViewFactory(layoutInflater)

    @Provides fun presenterFactory(
        screenNavigator: ScreenNavigator,
        findBlogItemService: FindBlogItemService,
        refreshViewsAndVotesService: RefreshViewsAndVotesService,
        backPressDispatcher: BackPressDispatcher
    ) = PresenterFactory(
        screenNavigator,
        findBlogItemService,
        refreshViewsAndVotesService,
        backPressDispatcher,
    )

    @Provides fun viewModelFactory(
        screenNavigator: ScreenNavigator,
        findBlogItemService: FindBlogItemService,
        refreshViewsAndVotesService: RefreshViewsAndVotesService,
    ) = ViewModelFactory(
        screenNavigator,
        findBlogItemService,
        refreshViewsAndVotesService,
    )

    @Provides fun screenNavigator() = activityComponent.screenNavigator()

    @Provides fun findBlogItemService() = activityComponent.findBlogItemService()

    @Provides fun refreshViewsAndVotesService() = activityComponent.refreshViewsAndVotesService()
}

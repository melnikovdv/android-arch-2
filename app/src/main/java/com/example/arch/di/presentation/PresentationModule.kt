package com.example.arch.di.presentation

import android.view.LayoutInflater
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.common.mvp.factory.MvpViewFactory
import com.example.arch.screen.common.mvp.factory.PresenterFactory
import com.example.arch.screen.common.mvvm.ViewModelFactory
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator
import dagger.Module
import dagger.Provides

@Module
class PresentationModule() {
    @Provides @PresentationScope fun mvpViewFactory(
        layoutInflater: LayoutInflater
    ) = MvpViewFactory(layoutInflater)

    @Provides @PresentationScope fun presenterFactory(
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

    @Provides @PresentationScope fun viewModelFactory(
        screenNavigator: ScreenNavigator,
        findBlogItemService: FindBlogItemService,
        refreshViewsAndVotesService: RefreshViewsAndVotesService,
    ) = ViewModelFactory(
        screenNavigator,
        findBlogItemService,
        refreshViewsAndVotesService,
    )
}

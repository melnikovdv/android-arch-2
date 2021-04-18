package com.example.arch.di

import com.example.arch.screen.common.mvp.factory.MvpViewFactory
import com.example.arch.screen.common.mvp.factory.PresenterFactory
import com.example.arch.screen.common.mvvm.ViewModelFactory
import com.example.arch.screen.common.nav.BackPressDispatcher

class PresentationCompositionRoot(
    private val activityCompositionRoot: ActivityCompositionRoot
) {
    val backPressDispatcher: BackPressDispatcher get() = activityCompositionRoot.activity

    val mvpViewFactory by lazy { MvpViewFactory(activityCompositionRoot.layoutInflater) }

    val presenterFactory by lazy {
        PresenterFactory(
            activityCompositionRoot.screenNavigator,
            activityCompositionRoot.findBlogItemService,
            activityCompositionRoot.refreshViewsAndVotesService,
            backPressDispatcher,
        )
    }

    val viewModelFactory by lazy {
        ViewModelFactory(
            activityCompositionRoot.screenNavigator,
            activityCompositionRoot.findBlogItemService,
            activityCompositionRoot.refreshViewsAndVotesService,
        )
    }

    val screenNavigator get() = activityCompositionRoot.screenNavigator
}

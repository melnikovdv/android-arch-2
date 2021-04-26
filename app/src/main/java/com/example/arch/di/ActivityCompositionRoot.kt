package com.example.arch.di

import android.view.LayoutInflater
import com.example.arch.screen.common.base.BaseActivity
import com.example.arch.screen.common.nav.ScreenNavigator

class ActivityCompositionRoot(
    val activity: BaseActivity,
    private val appCompositionRoot: AppCompositionRoot,
) {
    val application get() = appCompositionRoot.application

    val layoutInflater get() = LayoutInflater.from(activity)

    val fragmentManager get() = activity.supportFragmentManager

    val screenNavigator = ScreenNavigator(fragmentManager)

    val findBlogItemService = appCompositionRoot.findBlogItemService

    val refreshViewsAndVotesService = appCompositionRoot.refreshViewsAndVotesService

    val backPressDispatcher = activity
}

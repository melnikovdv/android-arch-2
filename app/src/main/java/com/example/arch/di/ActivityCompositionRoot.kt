package com.example.arch.di

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.arch.screen.common.base.BaseActivity
import com.example.arch.screen.common.nav.ScreenNavigator

class ActivityCompositionRoot(
    val activity: BaseActivity,
    private val appCompositionRoot: AppCompositionRoot,
    savedInstanceState: Bundle?
) {
    val application get() = appCompositionRoot.application

    val layoutInflater get() = LayoutInflater.from(activity)

    val fragmentManager get() = activity.supportFragmentManager

    val screenNavigator = ScreenNavigator(fragmentManager, savedInstanceState)

    val findBlogItemService = appCompositionRoot.findBlogItemService

    val refreshViewsAndVotesService = appCompositionRoot.refreshViewsAndVotesService

    val backPressDispatcher = activity
}

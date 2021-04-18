package com.example.arch.di.activity

import android.app.Application
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun application(): Application

    fun layoutInflater(): LayoutInflater

    fun fragmentManager(): FragmentManager

    fun screenNavigator(): ScreenNavigator

    fun findBlogItemService(): FindBlogItemService

    fun refreshViewsAndVotesService(): RefreshViewsAndVotesService

    fun backPressDispatcher(): BackPressDispatcher
}

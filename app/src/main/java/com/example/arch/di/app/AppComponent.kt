package com.example.arch.di.app

import android.app.Application
import com.example.arch.api.Api
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.util.Generator
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Component(
    modules = [AppModule::class]
) @Singleton interface AppComponent {
    fun application(): Application

    fun generator(): Generator

    fun api(): Api

    fun blogItemRepo(): BlogItemRepo

    fun ioDispatcher(): CoroutineDispatcher

    fun findBlogItemService(): FindBlogItemService

    fun refreshViewsAndVotesService(): RefreshViewsAndVotesService
}

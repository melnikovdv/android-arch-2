package com.example.arch

import android.app.Application
import com.example.arch.api.Api
import com.example.arch.api.ApiImpl
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.blog.repo.BlogItemRepoImpl
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.util.Generator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        Timber.d("onCreate")
    }

    val generator: Generator by lazy { Generator() }

    val api: Api by lazy { ApiImpl(generator) }

    val blogItemRepo: BlogItemRepo by lazy { BlogItemRepoImpl() }

    val findBlogItemService by lazy {
        FindBlogItemService(
            blogItemRepo,
            api,
            generator,
            Dispatchers.IO
        )
    }

    val refreshViewsAndVotesService by lazy {
        RefreshViewsAndVotesService(
            blogItemRepo,
            api,
            generator,
            Dispatchers.IO
        )
    }
}

package com.example.arch.di

import android.app.Application
import com.example.arch.api.Api
import com.example.arch.api.ApiImpl
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.blog.repo.BlogItemRepoImpl
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.util.Generator
import kotlinx.coroutines.Dispatchers

class AppCompositionRoot(val application: Application) {

    val generator by lazy { Generator() }

    val api: Api by lazy { ApiImpl(generator) }

    val blogItemRepo: BlogItemRepo by lazy { BlogItemRepoImpl() }

    val ioDispatcher by lazy { Dispatchers.IO }

    val findBlogItemService by lazy {
        FindBlogItemService(
            blogItemRepo,
            api,
            generator,
            ioDispatcher
        )
    }

    val refreshViewsAndVotesService by lazy {
        RefreshViewsAndVotesService(
            blogItemRepo,
            api,
            generator,
            ioDispatcher
        )
    }
}

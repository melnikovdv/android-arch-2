package com.example.arch.di.app

import com.example.arch.api.Api
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.util.Generator
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class ServiceModule() {

    @Provides @Singleton fun findBlogItemService(
        blogItemRepo: BlogItemRepo,
        api: Api,
        generator: Generator,
        ioDispatcher: CoroutineDispatcher
    ) = FindBlogItemService(blogItemRepo, api, generator, ioDispatcher)

    @Provides @Singleton fun refreshViewsAndVotesService(
        blogItemRepo: BlogItemRepo,
        api: Api,
        generator: Generator,
        ioDispatcher: CoroutineDispatcher
    ) = RefreshViewsAndVotesService(
        blogItemRepo,
        api,
        generator,
        ioDispatcher
    )

}

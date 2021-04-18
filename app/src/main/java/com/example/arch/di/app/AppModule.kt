package com.example.arch.di.app

import android.app.Application
import com.example.arch.api.Api
import com.example.arch.api.ApiImpl
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.blog.repo.BlogItemRepoImpl
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.util.Generator
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides fun application(): Application = application

    @Provides @Singleton fun generator() = Generator()

    @Provides @Singleton fun blogItemRepo(): BlogItemRepo = BlogItemRepoImpl()

    @Provides @Singleton fun api(generator: Generator): Api = ApiImpl(generator)

    @Provides fun ioDispatcher() = Dispatchers.IO

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

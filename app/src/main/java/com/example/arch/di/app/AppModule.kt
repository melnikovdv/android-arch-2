package com.example.arch.di.app

import com.example.arch.api.Api
import com.example.arch.api.ApiImpl
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.blog.repo.BlogItemRepoImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object AppModule {

    @Provides fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Module interface Binds {
        @dagger.Binds fun blogItemRepo(blogItemRepoImpl: BlogItemRepoImpl): BlogItemRepo
        @dagger.Binds fun api(apiImpl: ApiImpl): Api
    }
}

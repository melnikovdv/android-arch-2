package com.example.arch.di.app

import com.example.arch.api.Api
import com.example.arch.api.ApiImpl
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.blog.repo.BlogItemRepoImpl
import com.example.arch.di.qual.ApiToken
import com.example.arch.di.qual.MapboxToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module @InstallIn(SingletonComponent::class) object AppModule {

    @Provides fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides @ApiToken fun tokenApi(): String = "some_api_token_symbols"

    @Provides @MapboxToken fun tokenMapbox(): String = "some_mapbox_token_symbols"

    @Provides @Named("some") fun someString(): String = "some_string"

    @Module @InstallIn(SingletonComponent::class) interface Binds {
        @dagger.Binds fun blogItemRepo(blogItemRepoImpl: BlogItemRepoImpl): BlogItemRepo
        @dagger.Binds fun api(apiImpl: ApiImpl): Api
    }
}

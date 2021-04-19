package com.example.arch.api

import com.example.arch.api.dto.BlogItemDto
import com.example.arch.api.dto.BlogViewsAndVotesDto
import com.example.arch.blog.model.BlogItemId
import com.example.arch.util.Generator
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton class ApiImpl @Inject constructor(
    private val generatorProvider: Provider<Generator>
) : Api {
    override fun fetchBlogItem(id: BlogItemId): BlogItemDto {
        val generator = generatorProvider.get()
        return BlogItemDto(
            id.value,
            generator.string(10), generator.string(150),
            generator.nextInt(100), generator.nextInt(10), generator.nextInt(10),
            System.currentTimeMillis() - generator.nextInt(100) * 1000
        )
    }

    override fun fetchBlogViewsAndVotes(id: BlogItemId): BlogViewsAndVotesDto {
        val generator = generatorProvider.get()
        // emulating http request to server
        return BlogViewsAndVotesDto(
            id.value,
            generator.nextInt(100),
            generator.nextInt(10),
            generator.nextInt(10)
        )
    }
}

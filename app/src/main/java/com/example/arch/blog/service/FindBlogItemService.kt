package com.example.arch.blog.service

import com.example.arch.api.Api
import com.example.arch.api.dto.BlogItemDto
import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.util.Generator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


class FindBlogItemService(
    private val blogItemRepo: BlogItemRepo,
    private val api: Api,
    private val generator: Generator,
) {
    suspend fun find(id: BlogItemId): Result {
        return withContext(Dispatchers.IO) {
            try {
                blogItemRepo.findById(id)?.let { return@withContext Result.Success(it) }
                    ?: return@withContext fetchFromServer(id)
            } catch (t: Throwable) {
                return@withContext Result.Failure
            }
        }
    }

    private suspend fun fetchFromServer(id: BlogItemId): Result {
        delay(generator.timeoutMillis(5)) // emulating http request to server
        val blogItemDto = api.fetchBlogItem(id) ?: return Result.NotFound
        val blogItem = blogItemRepo.add(blogItemDto.toModel())
        return Result.Success(blogItem)
    }

    sealed class Result {
        data class Success(val blogItem: BlogItem) : Result()
        object Failure : Result()
        object NotFound : Result()
    }

    private fun BlogItemDto.toModel(): BlogItem = BlogItem(
        BlogItemId(id),
        title,
        text,
        viewCount,
        upVotes,
        downVotes,
        created,
        System.currentTimeMillis()
    )

    fun findAll(): List<BlogItem> {
        return blogItemRepo.findAll()
    }
}

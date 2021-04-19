package com.example.arch.blog.service

import com.example.arch.api.Api
import com.example.arch.api.dto.BlogItemDto
import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.util.Generator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class FindBlogItemService @Inject constructor(
    private val blogItemRepo: BlogItemRepo,
    private val api: Api,
    private val generator: Generator,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun find(id: BlogItemId): ServiceResult {
        return withContext(ioDispatcher) {
            try {
                blogItemRepo.findById(id)?.let { return@withContext ServiceResult.Success(it) }
                    ?: return@withContext fetchFromServer(id)
            } catch (t: Throwable) {
                return@withContext ServiceResult.Failure
            }
        }
    }

    private suspend fun fetchFromServer(id: BlogItemId): ServiceResult {
        delay(generator.timeoutMillis(3)) // emulating http request to server
        val blogItemDto = api.fetchBlogItem(id) ?: return ServiceResult.NotFound
        val blogItem = blogItemRepo.add(blogItemDto.toModel())
        return ServiceResult.Success(blogItem)
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

package com.example.arch.blog.service

import com.example.arch.api.Api
import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.util.Generator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RefreshViewsAndVotesService(
    private val blogItemRepo: BlogItemRepo,
    private val api: Api,
    private val generator: Generator,
) {

    suspend fun refresh(id: BlogItemId): Result {
        return withContext(Dispatchers.IO) {
            try {
                delay(generator.timeoutMillis(3)) // emulating http request to server
                val dto = api.fetchBlogViewsAndVotes(id) ?: return@withContext Result.NotFound
                val blogItemFromCache = blogItemRepo.findById(id)
                    ?: return@withContext Result.NotFound
                val updatedBlogItem = blogItemFromCache.copy(
                    viewCount = dto.viewCount,
                    upVotes = dto.upVotes,
                    downVotes = dto.downVotes,
                )
                blogItemRepo.update(updatedBlogItem)
                return@withContext Result.Success(updatedBlogItem)
            } catch (t: Throwable) {
                return@withContext Result.Failure
            }
        }
    }

    sealed class Result {
        data class Success(val blogItem: BlogItem) : Result()
        object Failure : Result()
        object NotFound : Result()
    }
}

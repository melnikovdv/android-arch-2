package com.example.arch.blog.service

import com.example.arch.api.Api
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.repo.BlogItemRepo
import com.example.arch.util.Generator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RefreshViewsAndVotesService(
    private val blogItemRepo: BlogItemRepo,
    private val api: Api,
    private val generator: Generator,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun refresh(id: BlogItemId): ServiceResult {
        return withContext(ioDispatcher) {
            try {
                delay(generator.timeoutMillis(3)) // emulating http request to server
                val dto =
                    api.fetchBlogViewsAndVotes(id) ?: return@withContext ServiceResult.NotFound
                val blogItemFromCache = blogItemRepo.findById(id)
                    ?: return@withContext ServiceResult.NotFound
                val updatedBlogItem = blogItemFromCache.copy(
                    viewCount = dto.viewCount,
                    upVotes = dto.upVotes,
                    downVotes = dto.downVotes,
                )
                blogItemRepo.update(updatedBlogItem)
                return@withContext ServiceResult.Success(updatedBlogItem)
            } catch (t: Throwable) {
                return@withContext ServiceResult.Failure
            }
        }
    }
}

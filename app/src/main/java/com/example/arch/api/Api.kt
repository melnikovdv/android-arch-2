package com.example.arch.api

import com.example.arch.api.dto.BlogItemDto
import com.example.arch.api.dto.BlogViewsAndVotesDto
import com.example.arch.blog.model.BlogItemId

interface Api {
    fun fetchBlogItem(id: BlogItemId): BlogItemDto?
    fun fetchBlogViewsAndVotes(id: BlogItemId): BlogViewsAndVotesDto?
}

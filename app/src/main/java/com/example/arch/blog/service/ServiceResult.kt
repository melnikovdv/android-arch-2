package com.example.arch.blog.service

import com.example.arch.blog.model.BlogItem

sealed class ServiceResult {
    data class Success(val blogItem: BlogItem) : ServiceResult()
    object Failure : ServiceResult()
    object NotFound : ServiceResult()
}

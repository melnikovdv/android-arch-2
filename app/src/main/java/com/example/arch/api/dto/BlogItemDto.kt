package com.example.arch.api.dto

data class BlogItemDto(
    val id: Long,
    val title: String,
    val text: String,
    val viewCount: Int,
    val upVotes: Int,
    val downVotes: Int,
    val created: Long
)

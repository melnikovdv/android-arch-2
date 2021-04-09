package com.example.arch.blog.model

data class BlogItem(
    val id: BlogItemId,
    val title: String,
    val text: String,
    val viewCount: Int,
    val upVotes: Int,
    val downVotes: Int,
    val created: Long,
    val updated: Long
)

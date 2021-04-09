package com.example.arch.blog.repo

import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId

interface BlogItemRepo {
    fun findAll(): List<BlogItem>
    fun findById(id: BlogItemId): BlogItem?
    fun add(blogItem: BlogItem): BlogItem
    fun update(blogItem: BlogItem)
    fun removeById(id: BlogItemId)
}


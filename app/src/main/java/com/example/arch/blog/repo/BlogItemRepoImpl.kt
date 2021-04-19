package com.example.arch.blog.repo

import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class BlogItemRepoImpl @Inject constructor() : BlogItemRepo {

    private val items: MutableMap<BlogItemId, BlogItem> = ConcurrentHashMap()

    override fun findAll(): List<BlogItem> {
        return ArrayList(items.values)
    }

    override fun findById(id: BlogItemId): BlogItem? {
        return items[id]
    }

    override fun add(blogItem: BlogItem): BlogItem {
        items[blogItem.id] = blogItem
        return blogItem
    }

    override fun update(blogItem: BlogItem) {
        items[blogItem.id] = blogItem
    }

    override fun removeById(id: BlogItemId) {
        items.remove(id)
    }
}

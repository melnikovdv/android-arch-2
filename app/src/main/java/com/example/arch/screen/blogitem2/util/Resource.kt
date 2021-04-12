package com.example.arch.screen.blogitem2.util

import com.example.arch.blog.model.BlogItem
import com.example.arch.screen.blogitem2.util.Status.*

typealias BlogItemResource = Resource<BlogItem>

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}

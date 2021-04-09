package com.example.arch.screen.blogitems.row

import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.common.mvp.MvpView

interface BlogItemsRowMvpView : MvpView {

    interface Listener {
        fun onBlogItemClicked(blogItemId: BlogItemId)
    }

    fun bindData(blogItem: BlogItem)
}

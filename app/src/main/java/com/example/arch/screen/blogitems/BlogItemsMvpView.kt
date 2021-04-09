package com.example.arch.screen.blogitems

import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.common.mvp.MvpViewObservable
import com.example.arch.screen.common.nav.BackPressedListener

interface BlogItemsMvpView : MvpViewObservable<BlogItemsMvpView.Listener> {

    interface Listener : BackPressedListener {
        fun onBlogItemClicked(blogItemId: BlogItemId)
    }

    fun bindData(blogItems: List<BlogItem>)
}

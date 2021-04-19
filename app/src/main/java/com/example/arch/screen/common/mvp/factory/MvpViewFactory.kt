package com.example.arch.screen.common.mvp.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.arch.di.presentation.PresentationScope
import com.example.arch.screen.blogitem.BlogItemMvpView
import com.example.arch.screen.blogitem.BlogItemMvpViewImpl
import com.example.arch.screen.blogitems.BlogItemsAdapter
import com.example.arch.screen.blogitems.BlogItemsMvpView
import com.example.arch.screen.blogitems.BlogItemsMvpViewImpl
import com.example.arch.screen.blogitems.row.BlogItemsRowMvpView
import com.example.arch.screen.blogitems.row.BlogItemsRowMvpViewImpl
import javax.inject.Inject

@PresentationScope class MvpViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater
) {
    fun createBlogItemMvpView(
        parent: ViewGroup?
    ): BlogItemMvpView {
        return BlogItemMvpViewImpl(layoutInflater, parent)
    }

    fun createBlogItemsAdapter(
        listener: BlogItemsRowMvpView.Listener
    ): BlogItemsAdapter {
        return BlogItemsAdapter(listener, this)
    }

    fun createBlogItemsView(
        parent: ViewGroup?,
    ): BlogItemsMvpView {
        return BlogItemsMvpViewImpl(layoutInflater, parent, this)
    }

    fun createBlogItemsRowView(
        parent: ViewGroup?,
        listener: BlogItemsRowMvpView.Listener
    ): BlogItemsRowMvpView {
        return BlogItemsRowMvpViewImpl(layoutInflater, parent, listener)
    }
}

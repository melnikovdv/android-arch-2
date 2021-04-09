package com.example.arch.screen.blogitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arch.R
import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.blogitems.row.BlogItemsRowMvpView
import com.example.arch.screen.common.mvp.MvpViewObservableBase

class BlogItemsMvpViewImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    MvpViewObservableBase<BlogItemsMvpView.Listener>(), BlogItemsMvpView,
    BlogItemsRowMvpView.Listener {

    override var rootView: View =
        layoutInflater.inflate(R.layout.blog_items_fragment, parent, false)
    private val rvBlogItems: RecyclerView = findViewById(R.id.blog_items_fragment__rvBlogItems)
    private val blogItemsAdapter = BlogItemsAdapter(layoutInflater, this)

    init {
        rvBlogItems.layoutManager = LinearLayoutManager(context)
        rvBlogItems.adapter = blogItemsAdapter
    }

    override fun bindData(blogItems: List<BlogItem>) {
        blogItemsAdapter.bindData(blogItems)
    }

    override fun onBlogItemClicked(blogItemId: BlogItemId) {
        for (listener in listeners) {
            listener.onBlogItemClicked(blogItemId)
        }
    }
}

package com.example.arch.screen.blogitems.row

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.arch.R
import com.example.arch.blog.model.BlogItem
import com.example.arch.screen.common.mvp.MvpViewBase

class BlogItemsRowMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val listener: BlogItemsRowMvpView.Listener
) : MvpViewBase(), BlogItemsRowMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.blog_items_row, parent, false)
    private val tvTitle: TextView = findViewById(R.id.blog_item_fragment__tvTitle)
    private val tvViewCount: TextView = findViewById(R.id.blog_item_fragment__tvViewCount)
    private val tvUpVotes: TextView = findViewById(R.id.blog_item_fragment__tvUpVotes)
    private val tvDownVotes: TextView = findViewById(R.id.blog_item_fragment__tvDownVotes)

    private lateinit var blogItem: BlogItem

    init {
        rootView.setOnClickListener { onBlogItemClicked() }
    }

    override fun bindData(blogItem: BlogItem) {
        this.blogItem = blogItem
        tvTitle.text = blogItem.title
        tvViewCount.text = blogItem.viewCount.toString()
        tvUpVotes.text = blogItem.upVotes.toString()
        tvDownVotes.text = blogItem.downVotes.toString()
    }

    private fun onBlogItemClicked() {
        listener.onBlogItemClicked(blogItem.id)
    }
}

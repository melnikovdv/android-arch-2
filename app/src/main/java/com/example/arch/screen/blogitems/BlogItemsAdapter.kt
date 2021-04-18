package com.example.arch.screen.blogitems

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.blogitems.BlogItemsAdapter.BlogItemViewHolder
import com.example.arch.screen.blogitems.row.BlogItemsRowMvpView
import com.example.arch.screen.common.mvp.factory.MvpViewFactory
import java.util.*

class BlogItemsAdapter(
    private val listener: BlogItemsRowMvpView.Listener,
    private val mvpViewFactory: MvpViewFactory,
) : RecyclerView.Adapter<BlogItemViewHolder>(), BlogItemsRowMvpView.Listener {

    class BlogItemViewHolder(val blogItemsRowMvpView: BlogItemsRowMvpView) :
        RecyclerView.ViewHolder(blogItemsRowMvpView.rootView)

    private val items: MutableList<BlogItem> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BlogItemViewHolder {
        val blogItemsRowMvpView = mvpViewFactory.createBlogItemsRowView(viewGroup, this)
        return BlogItemViewHolder(blogItemsRowMvpView)
    }

    override fun onBindViewHolder(blogItemViewHolder: BlogItemViewHolder, position: Int) {
        val blogItem = items[position]
        blogItemViewHolder.blogItemsRowMvpView.bindData(blogItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBlogItemClicked(blogItemId: BlogItemId) {
        listener.onBlogItemClicked(blogItemId)
    }

    fun bindData(blogItems: List<BlogItem>) {
        items.clear()
        items.addAll(blogItems)
        notifyDataSetChanged()
    }
}

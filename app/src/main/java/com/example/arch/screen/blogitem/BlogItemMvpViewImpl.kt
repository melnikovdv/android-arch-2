package com.example.arch.screen.blogitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.arch.R
import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.common.mvp.MvpViewObservableBase
import timber.log.Timber

class BlogItemMvpViewImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    MvpViewObservableBase<BlogItemMvpView.Listener>(), BlogItemMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.blog_item_fragment, parent, false)
    private val progress: ProgressBar = findViewById(R.id.blog_item_fragment__progress)
    private val llContent: LinearLayout = findViewById(R.id.blog_item_fragment__llContent)
    private val tvTitle: TextView = findViewById(R.id.blog_item_fragment__tvTitle)
    private val tvText: TextView = findViewById(R.id.blog_item_fragment__tvText)
    private val tvViewCount: TextView = findViewById(R.id.blog_item_fragment__tvViewCount)
    private val tvUpVotes: TextView = findViewById(R.id.blog_item_fragment__tvUpVotes)
    private val tvDownVotes: TextView = findViewById(R.id.blog_item_fragment__tvDownVotes)
    private val btnGoBack: Button = findViewById(R.id.blog_item_fragment__btnGoBack)
    private val btnRefresh: Button = findViewById(R.id.blog_item_fragment__btnRefresh)

    init {
        btnGoBack.setOnClickListener { goBack() }
        btnRefresh.setOnClickListener { refresh() }
    }

    private fun refresh() {
        listeners.forEach { it.onRefreshClicked() }
    }

    override fun showItemNotFound(id: BlogItemId) {
        showToast("BlogItem with id=$id not found")
    }

    override fun showError(id: BlogItemId) {
        showToast("Error fetching blogItem with id=$id")
    }

    private fun goBack() {
        listeners.forEach { it.onBtnGoBackClicked() }
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        llContent.visibility = View.GONE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        llContent.visibility = View.VISIBLE
    }

    override fun bindData(blogItem: BlogItem) {
        tvTitle.text = blogItem.title
        tvText.text = blogItem.text
        tvViewCount.text = blogItem.viewCount.toString()
        tvUpVotes.text = blogItem.upVotes.toString()
        tvDownVotes.text = blogItem.downVotes.toString()
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}

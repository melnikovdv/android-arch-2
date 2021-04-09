package com.example.arch.screen.blogitems

import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.screen.common.mvp.MvpPresenter
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator
import kotlinx.coroutines.*
import timber.log.Timber

class BlogItemsPresenter(
    private val screenNavigator: ScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val findBlogItemService: FindBlogItemService
) : MvpPresenter<BlogItemsMvpView>, BlogItemsMvpView.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var view: BlogItemsMvpView

    override fun bindView(view: BlogItemsMvpView) {
        this.view = view
        loadItems()
    }

    private fun loadItems() {
        coroutineScope.launch {
            val blogItems = findBlogItemService.findAll()
            onItemsLoaded(blogItems)
        }
    }

    private fun onItemsLoaded(blogItems: List<BlogItem>) {
        // prepare to show
        view.bindData(blogItems)
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        // dispose all requests
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onBackPressed(): Boolean {
        Timber.d("onBackPressed")
        screenNavigator.navigateUp()
        return true
    }

    override fun onBlogItemClicked(blogItemId: BlogItemId) {
        screenNavigator.toBlogItem(blogItemId)
    }
}

package com.example.arch.screen.blogitem

import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.common.mvp.MvpPresenter
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator
import kotlinx.coroutines.*
import timber.log.Timber

class BlogItemPresenter(
    private val id: BlogItemId,
    private val screenNavigator: ScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val findBlogItemService: FindBlogItemService,
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService,
) : MvpPresenter<BlogItemMvpView>, BlogItemMvpView.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var view: BlogItemMvpView

    override fun bindView(view: BlogItemMvpView) {
        this.view = view
        view.showProgress()
        loadItem()
    }

    private fun loadItem() {
        coroutineScope.launch {
            when (val result = findBlogItemService.find(id)) {
                FindBlogItemService.Result.Failure -> onError()
                FindBlogItemService.Result.NotFound -> onItemNotFound()
                is FindBlogItemService.Result.Success -> onItemLoaded(result.blogItem)
            }
        }
    }

    private fun onItemNotFound() {
        view.hideProgress()
        view.showItemNotFound(id)
    }

    private fun onError() {
        view.hideProgress()
        view.showError(id)
    }

    private fun onItemLoaded(blogItem: BlogItem) {
        // prepare to show
        view.hideProgress()
        view.bindData(blogItem)
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

    override fun onRefreshClicked() {
        Timber.d("onRefreshClicked")
        view.showProgress()
        coroutineScope.launch {
            when (val result = refreshViewsAndVotesService.refresh(id)) {
                RefreshViewsAndVotesService.Result.Failure -> onError()
                RefreshViewsAndVotesService.Result.NotFound -> onItemNotFound()
                is RefreshViewsAndVotesService.Result.Success -> onItemLoaded(result.blogItem)
            }
        }
    }

    override fun onBtnGoBackClicked() {
        screenNavigator.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        Timber.d("onBackPressed")
        screenNavigator.navigateUp()
        return true
    }
}

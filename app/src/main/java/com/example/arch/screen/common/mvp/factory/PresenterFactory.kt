package com.example.arch.screen.common.mvp.factory

import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.blogitem.BlogItemPresenter
import com.example.arch.screen.blogitems.BlogItemsPresenter
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator

class PresenterFactory(
    private val screenNavigator: ScreenNavigator,
    private val findBlogItemService: FindBlogItemService,
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService,
    private val backPressDispatcher: BackPressDispatcher
) {

    fun createBlogItemPresenter(
        id: BlogItemId,
    ): BlogItemPresenter {
        return BlogItemPresenter(
            id,
            screenNavigator,
            backPressDispatcher,
            findBlogItemService,
            refreshViewsAndVotesService
        )
    }

    fun createBlogItemsPresenter(): BlogItemsPresenter {
        return BlogItemsPresenter(
            screenNavigator,
            backPressDispatcher,
            findBlogItemService
        )
    }
}

package com.example.arch.screen.blogitem2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.common.nav.ScreenNavigator

class BlogItemViewModelFactory(
    private val blogItemId: BlogItemId,
    private val findBlogItemService: FindBlogItemService,
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService,
    private val screenNavigator: ScreenNavigator,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == BlogItemViewModel::class.java) {
            return BlogItemViewModel(
                blogItemId,
                findBlogItemService,
                refreshViewsAndVotesService,
                screenNavigator
            ) as T
        }
        throw IllegalStateException("Unknown ViewModel")
    }
}

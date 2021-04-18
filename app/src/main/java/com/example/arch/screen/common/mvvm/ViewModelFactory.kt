package com.example.arch.screen.common.mvvm

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.blogitem2.BlogItemMvvmFragment
import com.example.arch.screen.blogitem2.BlogItemViewModel
import com.example.arch.screen.common.nav.ScreenNavigator

class ViewModelFactory(
    private val screenNavigator: ScreenNavigator,
    private val findBlogItemService: FindBlogItemService,
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService,
) {
    fun createBlogItemViewModel(
        id: BlogItemId,
        blogItemMvvmFragment: BlogItemMvvmFragment
    ): BlogItemViewModel {
        return createViewModelProvider(id, blogItemMvvmFragment).get(BlogItemViewModel::class.java)
    }

    private fun createViewModelProvider(id: BlogItemId, fragment: Fragment): ViewModelProvider {
        return ViewModelProvider(fragment, createViewModelProviderFactory(id))
    }

    private fun createViewModelProviderFactory(id: BlogItemId): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass == BlogItemViewModel::class.java) {
                    val blogItemViewModel = BlogItemViewModel(
                        id,
                        findBlogItemService,
                        refreshViewsAndVotesService,
                        screenNavigator,
                    )
                    return blogItemViewModel as T
                }
                throw IllegalStateException("Unknown ViewModel")
            }
        }
    }


}

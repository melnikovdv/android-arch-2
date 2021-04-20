package com.example.arch.screen.common.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.arch.R
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.blogitem.BlogItemFragment
import com.example.arch.screen.blogitem2.BlogItemMvvmFragment
import com.example.arch.screen.blogitems.BlogItemsFragment
import com.example.arch.screen.root.RootFragment
import com.ncapdevi.fragnav.FragNavController
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped class ScreenNavigator @Inject constructor(
    fragmentManager: FragmentManager,
) : FragNavController.RootFragmentListener {

    private val fragNavController = FragNavController(fragmentManager, R.id.container)

    fun initialize(savedInstanceState: Bundle?) {
        fragNavController.rootFragmentListener = this
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    fun toBlogItem(id: BlogItemId) {
        fragNavController.pushFragment(BlogItemFragment.newInstance(id))
    }

    fun toBlogItems() {
        fragNavController.pushFragment(BlogItemsFragment.newInstance())
    }

    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment {
        return RootFragment.newInstance()
    }

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateUp() {
        fragNavController.popFragment()
    }

    fun toBlogItemVm(id: BlogItemId) {
        fragNavController.pushFragment(BlogItemMvvmFragment.newInstance(id))
    }
}

package com.example.arch.di

import com.example.arch.screen.blogitem.BlogItemFragment
import com.example.arch.screen.blogitem2.BlogItemMvvmFragment
import com.example.arch.screen.blogitems.BlogItemsFragment
import com.example.arch.screen.root.RootFragment

class Injector(
    private val compositionRoot: PresentationCompositionRoot
) {
    fun inject(fragment: BlogItemFragment) {
        fragment.presenterFactory = compositionRoot.presenterFactory
        fragment.mvpViewFactory = compositionRoot.mvpViewFactory
    }

    fun inject(fragment: BlogItemsFragment) {
        fragment.presenterFactory = compositionRoot.presenterFactory
        fragment.mvpViewFactory = compositionRoot.mvpViewFactory
    }

    fun inject(fragment: BlogItemMvvmFragment) {
        fragment.viewModelFactory = compositionRoot.viewModelFactory
        fragment.mvpViewFactory = compositionRoot.mvpViewFactory
        fragment.backPressDispatcher = compositionRoot.backPressDispatcher
    }

    fun inject(fragment: RootFragment) {
        fragment.screenNavigator = compositionRoot.screenNavigator
    }
}

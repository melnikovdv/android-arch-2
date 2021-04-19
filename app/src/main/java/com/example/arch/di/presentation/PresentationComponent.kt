package com.example.arch.di.presentation

import com.example.arch.screen.blogitem.BlogItemFragment
import com.example.arch.screen.blogitem2.BlogItemMvvmFragment
import com.example.arch.screen.blogitems.BlogItemsFragment
import com.example.arch.screen.root.RootFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [PresentationModule::class]
) @PresentationScope interface PresentationComponent {

    fun inject(blogItemFragment: BlogItemFragment)

    fun inject(blogItemFragment: BlogItemsFragment)

    fun inject(blogItemMvvmFragment: BlogItemMvvmFragment)

    fun inject(rootFragment: RootFragment)
}

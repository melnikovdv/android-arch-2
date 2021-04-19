package com.example.arch.di.presentation

import com.example.arch.di.activity.ActivityComponent
import com.example.arch.screen.blogitem.BlogItemFragment
import com.example.arch.screen.blogitem2.BlogItemMvvmFragment
import com.example.arch.screen.blogitems.BlogItemsFragment
import com.example.arch.screen.root.RootFragment
import dagger.Component

@Component(
    dependencies = [ActivityComponent::class],
    modules = [PresentationModule::class]
) @PresentationScope interface PresentationComponent {

    fun activityComponent(): ActivityComponent

    fun inject(blogItemFragment: BlogItemFragment)

    fun inject(blogItemFragment: BlogItemsFragment)

    fun inject(blogItemMvvmFragment: BlogItemMvvmFragment)

    fun inject(rootFragment: RootFragment)
}

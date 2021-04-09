package com.example.arch.screen.blogitem

import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.common.mvp.MvpViewObservable
import com.example.arch.screen.common.nav.BackPressedListener

interface BlogItemMvpView : MvpViewObservable<BlogItemMvpView.Listener> {

    interface Listener : BackPressedListener {
        fun onRefreshClicked()
        fun onBtnGoBackClicked()
    }

    fun bindData(blogItem: BlogItem)

    fun showProgress()
    fun hideProgress()
    fun showError(id: BlogItemId)
    fun showItemNotFound(id: BlogItemId)
}

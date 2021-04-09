package com.example.arch.screen.blogitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.screen.common.base.BaseFragment

class BlogItemsFragment : BaseFragment() {

    private lateinit var presenter: BlogItemsPresenter
    private val findBlogItemService: FindBlogItemService
        get() = app.findBlogItemService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = BlogItemsPresenter(
            screenNavigator = mainActivity.screenNavigator,
            backPressDispatcher = mainActivity,
            findBlogItemService = findBlogItemService
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: BlogItemsMvpView = BlogItemsMvpViewImpl(inflater, container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment {
            return BlogItemsFragment()
        }
    }
}

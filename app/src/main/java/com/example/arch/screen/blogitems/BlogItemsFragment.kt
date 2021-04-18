package com.example.arch.screen.blogitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arch.di.Service
import com.example.arch.screen.common.base.BaseFragment
import com.example.arch.screen.common.mvp.factory.MvpViewFactory
import com.example.arch.screen.common.mvp.factory.PresenterFactory

class BlogItemsFragment : BaseFragment() {

    @field:Service private lateinit var presenterFactory: PresenterFactory
    @field:Service private lateinit var mvpViewFactory: MvpViewFactory

    private lateinit var presenter: BlogItemsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createBlogItemsPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = mvpViewFactory.createBlogItemsView(container)
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

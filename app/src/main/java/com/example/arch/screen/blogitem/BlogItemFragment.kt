package com.example.arch.screen.blogitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arch.blog.model.BlogItemId
import com.example.arch.di.Service
import com.example.arch.screen.common.base.BaseFragment
import com.example.arch.screen.common.mvp.factory.MvpViewFactory
import com.example.arch.screen.common.mvp.factory.PresenterFactory

class BlogItemFragment : BaseFragment() {

    private lateinit var presenter: BlogItemPresenter

    @field:Service private lateinit var presenterFactory: PresenterFactory
    @field:Service private lateinit var mvpViewFactory: MvpViewFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        val id = BlogItemId(arguments!!.getLong(ARG_ITEM_ID))
        presenter = presenterFactory.createBlogItemPresenter(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = mvpViewFactory.createBlogItemMvpView(container)
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

        const val ARG_ITEM_ID = "itemId"

        fun newInstance(itemId: BlogItemId): Fragment {
            val fragment: Fragment = BlogItemFragment()
            val args = Bundle()
            args.putLong(ARG_ITEM_ID, itemId.value)
            fragment.arguments = args
            return fragment
        }
    }
}

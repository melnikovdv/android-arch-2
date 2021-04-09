package com.example.arch.screen.blogitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.common.MainActivity
import com.example.arch.screen.common.base.BaseFragment
import com.example.arch.screen.common.nav.BackPressDispatcher

class BlogItemFragment : BaseFragment() {

    private lateinit var presenter: BlogItemPresenter
    private val findBlogItemService: FindBlogItemService
        get() = app.findBlogItemService
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService
        get() = app.refreshViewsAndVotesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments!!.getLong(ARG_ITEM_ID)
        presenter = BlogItemPresenter(
            id = BlogItemId(id),
            screenNavigator = mainActivity.screenNavigator,
            backPressDispatcher = mainActivity,
            findBlogItemService = findBlogItemService,
            refreshViewsAndVotesService = refreshViewsAndVotesService
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: BlogItemMvpView = BlogItemMvpViewImpl(inflater, container)
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

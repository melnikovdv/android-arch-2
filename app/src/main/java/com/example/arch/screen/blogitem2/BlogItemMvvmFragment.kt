package com.example.arch.screen.blogitem2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.arch.R
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.databinding.BlogItemDatabindingFragmentBinding
import com.example.arch.screen.blogitem2.util.Status
import com.example.arch.screen.common.base.BaseFragment


class BlogItemMvvmFragment : BaseFragment() {

    private val findBlogItemService: FindBlogItemService
        get() = app.findBlogItemService
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService
        get() = app.refreshViewsAndVotesService

    private lateinit var blogItemViewModel: BlogItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val blogItemId = BlogItemId(arguments!!.getLong(ARG_ITEM_ID))
        val factory = BlogItemViewModelFactory(
            blogItemId,
            findBlogItemService,
            refreshViewsAndVotesService,
            mainActivity.screenNavigator
        )
        blogItemViewModel = ViewModelProvider(this, factory).get(BlogItemViewModel::class.java)
        blogItemViewModel.loadItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BlogItemDatabindingFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.blog_item_databinding_fragment, container, false
        )
        binding.vm = blogItemViewModel
        binding.lifecycleOwner = this
        setupVmToShowToastOnError()
        return binding.root
    }

    private fun setupVmToShowToastOnError() {
        blogItemViewModel.blogItemLiveData.observe(this, { blogItemResource ->
            if (blogItemResource.status == Status.ERROR) {
                showToast("" + blogItemResource.message)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        mainActivity.registerListener(blogItemViewModel)
    }

    override fun onStop() {
        super.onStop()
        mainActivity.unregisterListener(blogItemViewModel)
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    companion object {

        const val ARG_ITEM_ID = "itemId"

        fun newInstance(blogItemId: BlogItemId): Fragment {
            val fragment: Fragment = BlogItemMvvmFragment()
            val args = Bundle()
            args.putLong(ARG_ITEM_ID, blogItemId.value)
            fragment.arguments = args
            return fragment
        }
    }
}

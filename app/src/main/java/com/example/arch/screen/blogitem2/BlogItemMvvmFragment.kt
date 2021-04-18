package com.example.arch.screen.blogitem2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.arch.R
import com.example.arch.blog.model.BlogItemId
import com.example.arch.databinding.BlogItemDatabindingFragmentBinding
import com.example.arch.screen.blogitem2.util.Status
import com.example.arch.screen.common.base.BaseFragment
import com.example.arch.screen.common.mvvm.ViewModelFactory
import com.example.arch.screen.common.nav.BackPressDispatcher
import javax.inject.Inject


class BlogItemMvvmFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var backPressDispatcher: BackPressDispatcher

    private lateinit var blogItemViewModel: BlogItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)
        val blogItemId = BlogItemId(arguments!!.getLong(ARG_ITEM_ID))
        blogItemViewModel = viewModelFactory.createBlogItemViewModel(blogItemId, this)
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
        backPressDispatcher.registerListener(blogItemViewModel)
    }

    override fun onStop() {
        super.onStop()
        backPressDispatcher.unregisterListener(blogItemViewModel)
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

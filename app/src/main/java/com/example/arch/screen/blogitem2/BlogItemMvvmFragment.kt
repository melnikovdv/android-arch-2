package com.example.arch.screen.blogitem2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.arch.R
import com.example.arch.blog.model.BlogItem
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.blogitem2.util.BlogItemResource
import com.example.arch.screen.blogitem2.util.Status
import com.example.arch.screen.common.base.BaseFragment

class BlogItemMvvmFragment : BaseFragment() {

    private val findBlogItemService: FindBlogItemService
        get() = app.findBlogItemService
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService
        get() = app.refreshViewsAndVotesService

    private lateinit var progress: ProgressBar
    private lateinit var llContent: LinearLayout
    private lateinit var tvTitle: TextView
    private lateinit var tvText: TextView
    private lateinit var tvViewCount: TextView
    private lateinit var tvUpVotes: TextView
    private lateinit var tvDownVotes: TextView
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
        blogItemViewModel.blogItemLiveData.observe(
            this,
            { blogItemResource -> bindData(blogItemResource) }
        )
        blogItemViewModel.loadItem()
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            progress.visibility = View.VISIBLE
            llContent.visibility = View.GONE
        } else {
            progress.visibility = View.GONE
            llContent.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.blog_item_fragment, container, false)
        progress = rootView.findViewById(R.id.blog_item_fragment__progress)
        llContent = rootView.findViewById(R.id.blog_item_fragment__llContent)
        tvTitle = rootView.findViewById(R.id.blog_item_fragment__tvTitle)
        tvText = rootView.findViewById(R.id.blog_item_fragment__tvText)
        tvViewCount = rootView.findViewById(R.id.blog_item_fragment__tvViewCount)
        tvUpVotes = rootView.findViewById(R.id.blog_item_fragment__tvUpVotes)
        tvDownVotes = rootView.findViewById(R.id.blog_item_fragment__tvDownVotes)
        val btnGoBack = rootView.findViewById<Button>(R.id.blog_item_fragment__btnGoBack)
        btnGoBack.setOnClickListener { blogItemViewModel.onGoBackClicked() }
        val btnRefresh = rootView.findViewById<Button>(R.id.blog_item_fragment__btnRefresh)
        btnRefresh.setOnClickListener { blogItemViewModel.onRefreshClicked() }
        return rootView
    }

    private fun bindData(blogItemResource: BlogItemResource) {
        when (blogItemResource.status) {
            Status.SUCCESS -> bindBlogItem(blogItemResource.data!!)
            Status.ERROR -> showToast(blogItemResource.message!!)
            Status.LOADING -> showLoading(true)
        }
    }

    private fun bindBlogItem(blogItem: BlogItem) {
        showLoading(false)
        tvTitle.text = blogItem.title
        tvText.text = blogItem.text
        tvViewCount.text = blogItem.viewCount.toString()
        tvUpVotes.text = blogItem.upVotes.toString()
        tvDownVotes.text = blogItem.downVotes.toString()
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

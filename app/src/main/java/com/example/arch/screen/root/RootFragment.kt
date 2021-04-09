package com.example.arch.screen.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.arch.R
import com.example.arch.blog.model.BlogItemId
import com.example.arch.screen.common.base.BaseFragment


class RootFragment : BaseFragment() {

    private lateinit var btnBlogItem: Button
    private lateinit var btnBlogItems: Button
    private lateinit var edtBlogItemId: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.root_fragment, container, false)
        btnBlogItem = rootView.findViewById(R.id.root_fragment__btnBlogItem)
        edtBlogItemId = rootView.findViewById(R.id.root_fragment__edtBlogItemId)
        btnBlogItems = rootView.findViewById(R.id.root_fragment__btnBlogItems)
        setup()
        return rootView
    }

    private fun setup() {
        btnBlogItem.setOnClickListener {
            val id = BlogItemId(edtBlogItemId.text.toString().toLong())
            mainActivity.screenNavigator.toBlogItem(id)
        }
        btnBlogItems.setOnClickListener { mainActivity.screenNavigator.toBlogItems() }
    }

    companion object {
        fun newInstance() = RootFragment()
    }
}

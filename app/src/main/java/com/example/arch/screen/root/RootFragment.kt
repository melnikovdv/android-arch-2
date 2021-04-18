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
import com.example.arch.screen.common.nav.ScreenNavigator


class RootFragment : BaseFragment() {

    private lateinit var btnBlogItem: Button
    private lateinit var btnBlogItems: Button
    private lateinit var edtBlogItemId: EditText
    private lateinit var btnBlogItemVm: Button
    private lateinit var edtBlogItemIdVm: EditText
    private lateinit var btnBlogItemIdInc: Button
    private lateinit var btnBlogItemIdVmInc: Button

    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)
        screenNavigator = presentationComponent.activityComponent().screenNavigator()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.root_fragment, container, false)
        btnBlogItem = rootView.findViewById(R.id.root_fragment__btnBlogItem)
        edtBlogItemId = rootView.findViewById(R.id.root_fragment__edtBlogItemId)
        btnBlogItems = rootView.findViewById(R.id.root_fragment__btnBlogItems)
        btnBlogItemVm = rootView.findViewById(R.id.root_fragment__btnBlogItemVm)
        edtBlogItemIdVm = rootView.findViewById(R.id.root_fragment__edtBlogItemIdVm)
        btnBlogItemIdInc = rootView.findViewById(R.id.root_fragment__btnBlogItemIdInc)
        btnBlogItemIdVmInc = rootView.findViewById(R.id.root_fragment__btnBlogItemIdVmInc)
        setup()
        return rootView
    }

    private fun setup() {
        btnBlogItem.setOnClickListener {
            val id = BlogItemId(edtBlogItemId.text.toString().toLong())
            screenNavigator.toBlogItem(id)
        }
        btnBlogItems.setOnClickListener { screenNavigator.toBlogItems() }
        btnBlogItemVm.setOnClickListener {
            val id = BlogItemId(edtBlogItemIdVm.text.toString().toLong())
            screenNavigator.toBlogItemVm(id)
        }
        btnBlogItemIdInc.setOnClickListener {
            edtBlogItemId.setText((edtBlogItemId.text.toString().toInt() + 1).toString())
        }
        btnBlogItemIdVmInc.setOnClickListener {
            edtBlogItemIdVm.setText((edtBlogItemIdVm.text.toString().toInt() + 1).toString())
        }
    }

    companion object {
        fun newInstance() = RootFragment()
    }
}

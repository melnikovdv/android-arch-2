package com.example.arch.screen.common.base

import androidx.fragment.app.Fragment
import com.example.arch.App
import com.example.arch.screen.common.MainActivity

abstract class BaseFragment : Fragment() {

    protected val app: App get() = activity?.application as App

    protected val mainActivity get() = activity as MainActivity
}

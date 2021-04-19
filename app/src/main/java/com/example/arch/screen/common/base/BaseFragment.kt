package com.example.arch.screen.common.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent
            .newPresentationComponent()
    }
}

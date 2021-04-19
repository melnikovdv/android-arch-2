package com.example.arch.screen.common.base

import androidx.fragment.app.Fragment
import com.example.arch.di.presentation.DaggerPresentationComponent
import com.example.arch.di.presentation.PresentationModule

abstract class BaseFragment : Fragment() {

    protected val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .activityComponent((requireActivity() as BaseActivity).activityComponent)
            .presentationModule(PresentationModule())
            .build()
    }
}

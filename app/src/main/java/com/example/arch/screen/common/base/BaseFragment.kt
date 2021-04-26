package com.example.arch.screen.common.base

import androidx.fragment.app.Fragment
import com.example.arch.di.Injector
import com.example.arch.di.PresentationCompositionRoot

abstract class BaseFragment : Fragment() {

    protected val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}

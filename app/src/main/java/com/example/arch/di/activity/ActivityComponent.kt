package com.example.arch.di.activity

import com.example.arch.di.presentation.PresentationComponent
import com.example.arch.screen.common.base.BaseActivity
import dagger.Subcomponent

@Subcomponent(
    modules = [ActivityModule::class]
) @ActivityScope interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

    fun inject(baseActivity: BaseActivity)
}

package com.example.arch.di.activity

import android.os.Bundle
import com.example.arch.di.presentation.PresentationComponent
import com.example.arch.screen.common.base.BaseActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ActivityModule::class,
        ActivityModule.Binds::class,
    ]
) @ActivityScope interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

    @Subcomponent.Builder interface Builder {
        @BindsInstance fun activity(baseActivity: BaseActivity): Builder
        @BindsInstance fun savedInstanceState(bundle: Bundle?): Builder
        fun build(): ActivityComponent
    }

    fun inject(baseActivity: BaseActivity)
}

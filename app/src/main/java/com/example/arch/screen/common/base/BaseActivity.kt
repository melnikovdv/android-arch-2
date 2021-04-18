package com.example.arch.screen.common.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.arch.App
import com.example.arch.di.activity.ActivityComponent
import com.example.arch.di.activity.ActivityModule
import com.example.arch.di.activity.DaggerActivityComponent
import com.example.arch.di.presentation.DaggerPresentationComponent
import com.example.arch.di.presentation.PresentationComponent
import com.example.arch.di.presentation.PresentationModule
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.BackPressedListener

abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    private val appComponent get() = (application as App).appComponent

    lateinit var activityComponent: ActivityComponent
        private set

    lateinit var presentationComponent: PresentationComponent
        private set

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this, appComponent, savedInstanceState))
            .build()
        activityComponent.screenNavigator()
        presentationComponent = DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityComponent))
            .build()

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        activityComponent.screenNavigator().onSaveInstanceState(outState)
    }

    override fun registerListener(listener: BackPressedListener) {
        backPressedListeners.add(listener);
    }

    override fun unregisterListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }

    override fun onBackPressed() {
        var isBackPressConsumedByAnyListener = false
        backPressedListeners.forEach { listener ->
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true
            }
        }
        if (!isBackPressConsumedByAnyListener) {
            super.onBackPressed()
        }
    }
}

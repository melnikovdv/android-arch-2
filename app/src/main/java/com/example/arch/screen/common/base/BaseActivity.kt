package com.example.arch.screen.common.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.arch.App
import com.example.arch.di.activity.ActivityComponent
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.BackPressedListener
import com.example.arch.screen.common.nav.ScreenNavigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    lateinit var activityComponent: ActivityComponent
        private set

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    @Inject lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = (application as App).appComponent
            .newActivityComponentBuilder()
            .activity(this)
            .savedInstanceState(savedInstanceState)
            .build()
        activityComponent.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        screenNavigator.onSaveInstanceState(outState)
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

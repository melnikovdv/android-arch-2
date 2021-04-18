package com.example.arch.screen.common.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.arch.App
import com.example.arch.di.ActivityCompositionRoot
import com.example.arch.di.PresentationCompositionRoot
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.BackPressedListener

abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    private val appCompositionRoot get() = (application as App).appCompositionRoot

    lateinit var activityCompositionRoot: ActivityCompositionRoot
        private set

    val presentationCompositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCompositionRoot = ActivityCompositionRoot(
            this, appCompositionRoot, savedInstanceState
        )
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        activityCompositionRoot.screenNavigator.onSaveInstanceState(outState)
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

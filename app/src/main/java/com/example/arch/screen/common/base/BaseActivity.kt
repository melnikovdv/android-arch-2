package com.example.arch.screen.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.arch.di.qual.ApiToken
import com.example.arch.di.qual.MapboxToken
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.BackPressedListener
import com.example.arch.screen.common.nav.ScreenNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    @Inject lateinit var screenNavigator: ScreenNavigator
    @Inject @ApiToken lateinit var apiToken: String
    @Inject @MapboxToken lateinit var mapboxToken: String
    @Inject @Named("some") lateinit var someString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenNavigator.initialize(savedInstanceState)
        Timber.d("Qualifier api: $apiToken")
        Timber.d("Qualifier mapbox: $mapboxToken")
        Timber.d("Qualifier some: $someString")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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

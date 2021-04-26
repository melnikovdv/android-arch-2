package com.example.arch.screen.common

import android.os.Bundle
import com.example.arch.R
import com.example.arch.screen.common.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityCompositionRoot.screenNavigator.init(savedInstanceState)
    }
}

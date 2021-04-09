package com.example.arch.screen.common.nav

interface BackPressDispatcher {
    fun registerListener(listener: BackPressedListener)
    fun unregisterListener(listener: BackPressedListener)
}

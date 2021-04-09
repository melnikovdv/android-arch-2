package com.example.arch.screen.common.mvp

abstract class MvpViewObservableBase<ListenerType>
    : MvpViewBase(), MvpViewObservable<ListenerType> {

    private val _listeners: MutableSet<ListenerType> = HashSet()

    override fun registerListener(listener: ListenerType) {
        _listeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        _listeners.remove(listener)
    }

    protected val listeners: Set<ListenerType>
        get() = _listeners
}

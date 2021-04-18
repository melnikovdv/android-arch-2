package com.example.arch.di

import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.screen.common.mvp.factory.MvpViewFactory
import com.example.arch.screen.common.mvp.factory.PresenterFactory
import com.example.arch.screen.common.mvvm.ViewModelFactory
import com.example.arch.screen.common.nav.BackPressDispatcher
import com.example.arch.screen.common.nav.ScreenNavigator
import java.lang.reflect.Field

class Injector(
    private val compositionRoot: PresentationCompositionRoot
) {
    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            ScreenNavigator::class.java -> {
                return compositionRoot.screenNavigator
            }
            FindBlogItemService::class.java -> {
                return compositionRoot.findBlogItemService
            }
            RefreshViewsAndVotesService::class.java -> {
                return compositionRoot.refreshViewsAndVotesService
            }
            MvpViewFactory::class.java -> {
                return compositionRoot.mvpViewFactory
            }
            PresenterFactory::class.java -> {
                return compositionRoot.presenterFactory
            }
            BackPressDispatcher::class.java -> {
                return compositionRoot.backPressDispatcher
            }
            ViewModelFactory::class.java -> {
                return compositionRoot.viewModelFactory
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}

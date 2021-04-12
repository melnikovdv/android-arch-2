package com.example.arch.screen.blogitem2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arch.blog.model.BlogItemId
import com.example.arch.blog.service.FindBlogItemService
import com.example.arch.blog.service.RefreshViewsAndVotesService
import com.example.arch.blog.service.ServiceResult
import com.example.arch.screen.blogitem2.util.BlogItemResource
import com.example.arch.screen.blogitem2.util.Status
import com.example.arch.screen.common.nav.BackPressedListener
import com.example.arch.screen.common.nav.ScreenNavigator
import kotlinx.coroutines.launch
import timber.log.Timber

class BlogItemViewModel(
    private val blogItemId: BlogItemId,
    private val findBlogItemService: FindBlogItemService,
    private val refreshViewsAndVotesService: RefreshViewsAndVotesService,
    private val screenNavigator: ScreenNavigator,
) : ViewModel(), BackPressedListener {

    private val _blogItemLiveData = MutableLiveData<BlogItemResource>()

    val blogItemLiveData: LiveData<BlogItemResource> = _blogItemLiveData

    fun loadItem() {
        viewModelScope.launch {
            postLoadingResource()
            val result = findBlogItemService.find(blogItemId)
            val resourceByResult = resourceByResult(result)
            postLiveData(resourceByResult)
        }
    }

    private fun postLoadingResource() {
        val resource = BlogItemResource(Status.LOADING, null, null)
        postLiveData(resource)
    }

    private fun resourceByResult(serviceResult: ServiceResult): BlogItemResource {
        return when (serviceResult) {
            ServiceResult.Failure ->
                BlogItemResource(Status.ERROR, null, "failed to fetch")
            ServiceResult.NotFound ->
                BlogItemResource(Status.ERROR, null, "not found")
            is ServiceResult.Success ->
                BlogItemResource(Status.SUCCESS, serviceResult.blogItem, null)
        }
    }

    private fun postLiveData(blogItemResource: BlogItemResource) {
        _blogItemLiveData.postValue(blogItemResource)
    }

    override fun onCleared() {
        Timber.d("onCleared")
//        No need to cancel cause of viewModelScope
//        viewModelScope.coroutineContext.cancelChildren()
    }

    fun onGoBackClicked() {
        internalGoBack()
    }

    private fun internalGoBack() {
        screenNavigator.navigateUp()
    }

    fun onRefreshClicked() {
        Timber.d("onRefreshClicked")
        viewModelScope.launch {
            postLoadingResource()
            val result = refreshViewsAndVotesService.refresh(blogItemId)
            val resourceByResult = resourceByResult(result)
            postLiveData(resourceByResult)
        }
    }

    override fun onBackPressed(): Boolean {
        internalGoBack()
        return true
    }
}

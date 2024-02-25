package com.mayursarode.newsapp.ui.topheadlines.offline

import androidx.lifecycle.viewModelScope
import com.mayursarode.newsapp.data.local.entity.Article
import com.mayursarode.newsapp.data.repository.TopHeadlinesRepository
import com.mayursarode.newsapp.ui.base.BaseViewModel
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.utils.Constants.DEFAULT_COUNTRY
import com.mayursarode.newsapp.utils.DispatcherProvider
import com.mayursarode.newsapp.utils.logger.Logger
import com.mayursarode.newsapp.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesOfflineViewModel @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider,
    private val logger: Logger
) : BaseViewModel() {
    companion object {
        const val TAG = "TopHeadlinesOfflineViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        if (networkHelper.isNetworkConnected()) {
            fetchNews()
        } else {
            fetchNewsDirectlyFromDB()
        }
    }

    fun fetchNews() {
        viewModelScope.launch {
            topHeadlinesRepository.getTopHeadlinesOffline(DEFAULT_COUNTRY)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                    logger.d(TAG, "fetchNews: ${e.message.toString()}")
                }.collect {
                    _uiState.value = UiState.Success(it)
                    logger.d(TAG, it.toString())
                }
        }
    }

    private fun fetchNewsDirectlyFromDB() {
        viewModelScope.launch {
            topHeadlinesRepository.getArticlesDirectlyFromDB()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                    logger.d(TAG, "fetchNews: ${e.message.toString()}")
                }.collect {
                    _uiState.value = UiState.Success(it)
                    logger.d(TAG, it.toString())
                }
        }
    }
}
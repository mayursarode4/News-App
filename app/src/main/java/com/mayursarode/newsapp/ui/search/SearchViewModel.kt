package com.mayursarode.newsapp.ui.search

import androidx.lifecycle.viewModelScope
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.data.repository.SearchRepository
import com.mayursarode.newsapp.ui.base.BaseViewModel
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.utils.Constants.DEBOUNCE_TIMEOUT
import com.mayursarode.newsapp.utils.Constants.MIN_SEARCH_CHAR
import com.mayursarode.newsapp.utils.DispatcherProvider
import com.mayursarode.newsapp.utils.ResourceProvider
import com.mayursarode.newsapp.utils.logger.Logger
import com.mayursarode.newsapp.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
    private val resourceProvider: ResourceProvider,
    private val logger: Logger
) : BaseViewModel() {

    companion object {
        const val TAG = "SearchViewModel"
    }

    private val _uiState =
        MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    private val searchText = MutableStateFlow("")

    init {
        setUpSearchStateFlow()
    }

    fun searchNews(searchQuery: String) {
        searchText.value = searchQuery
    }

    private fun setUpSearchStateFlow() {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                val errorText = resourceProvider.getStringNoInternetAvailable()
                _uiState.value = UiState.Error(errorText)
                return@launch
            } else {
                searchText.debounce(DEBOUNCE_TIMEOUT)
                    .filter { query ->
                        if (query.isNotEmpty() && query.length >= MIN_SEARCH_CHAR) {
                            return@filter true
                        } else {
                            _uiState.value = UiState.Success(emptyList())
                            return@filter false
                        }
                    }
                    .distinctUntilChanged()
                    .flatMapLatest {
                        _uiState.value = UiState.Loading
                        return@flatMapLatest searchRepository.getNewsBySearch(it)
                            .catch { e ->
                                // handle error
                                _uiState.value = UiState.Error(e.toString())
                                logger.d(
                                    "SearchViewModel",
                                    "fetchNewsBySearch: error: ${e.message.toString()} "
                                )
                            }
                    }
                    .flowOn(dispatcherProvider.io)
                    .collect {
                        // handle response and empty response properly
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, it.toString())
                    }
            }
        }

    }

}
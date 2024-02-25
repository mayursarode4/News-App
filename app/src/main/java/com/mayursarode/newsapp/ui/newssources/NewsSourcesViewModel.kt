package com.mayursarode.newsapp.ui.newssources

import androidx.lifecycle.viewModelScope
import com.mayursarode.newsapp.data.model.ApiSource
import com.mayursarode.newsapp.data.repository.NewsSourcesRepository
import com.mayursarode.newsapp.ui.base.BaseViewModel
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.utils.ResourceProvider
import com.mayursarode.newsapp.utils.logger.Logger
import com.mayursarode.newsapp.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val newsSourcesRepository: NewsSourcesRepository,
    private val networkHelper: NetworkHelper,
    private val resourceProvider: ResourceProvider,
    private val logger: Logger
) : BaseViewModel() {
    companion object {
        const val TAG = "NewsSourcesViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<List<ApiSource>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiSource>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                val errorText = resourceProvider.getStringNoInternetAvailable()
                _uiState.value = UiState.Error(errorText)
                return@launch
            } else {
                newsSourcesRepository.getNewsSources().catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                    logger.d(TAG, "fetchNewsSources: ${e.message.toString()}")
                }.collect {
                    _uiState.value = UiState.Success(it)
                    logger.d(TAG, it.toString())
                }
            }
        }
    }
}
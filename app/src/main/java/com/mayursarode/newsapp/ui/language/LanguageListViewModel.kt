package com.mayursarode.newsapp.ui.language

import androidx.lifecycle.viewModelScope
import com.mayursarode.newsapp.data.model.Language
import com.mayursarode.newsapp.data.repository.LanguageRepository
import com.mayursarode.newsapp.ui.base.BaseViewModel
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.utils.DispatcherProvider
import com.mayursarode.newsapp.utils.ResourceProvider
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
class LanguageListViewModel @Inject constructor(
    private val languageRepository: LanguageRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
    private val resourceProvider: ResourceProvider,
    private val logger: Logger
) : BaseViewModel() {

    companion object {
        const val TAG = "LanguageListViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Language>>> = _uiState

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                val errorText = resourceProvider.getStringNoInternetAvailable()
                _uiState.value = UiState.Error(errorText)
                return@launch
            } else {
                _uiState.value = UiState.Loading
                languageRepository.getLanguages()
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d(TAG, "fetchLanguages: ${e.message.toString()}")
                    }.collect {
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, it.toString())
                    }
            }
        }
    }

}